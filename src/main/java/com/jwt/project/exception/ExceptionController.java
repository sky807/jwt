package com.jwt.project.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Exception 처리가 이뤄지면 이쪽 로직을 타게되어
 * 컨트롤러 하는 부분
 *
 * @author 송하늘
 */
@Slf4j
@ControllerAdvice
public class ExceptionController {

    /**
     * 미리 설정해둔 ApiException이 발생하면 여기로온다.
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> apiException(ApiException e, HttpServletRequest request){
        log.warn("apiException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        ErrorResponse response = new ErrorResponse(e.getError().getCode(), e.getError().getDescription());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /**
     * @valid 유효성체크에 통과하지 못하면  MethodArgumentNotValidException 이 발생한다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.warn("MethodArgumentNotValidException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    private ErrorResponse makeErrorResponse(BindingResult bindingResult){
        String code = "";
        String description = "";
        String detail = "";

        //에러가 있다면
        if(bindingResult.hasErrors()){
            //DTO에 설정한 meaasge값을 가져온다
            detail = bindingResult.getFieldError().getDefaultMessage();

            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();

            switch (bindResultCode){
                case "NotBlank": //필수파라미터 값 입력안했을때
                    code = ErrorCode.NOT_BLANK.getCode();
                    description = ErrorCode.NOT_BLANK.getDescription();
                    break;

            }
        }

        return new ErrorResponse(code, description, detail);
    }
}
