package net.app315.hydra.example.server.handler;

import com.alibaba.excel.exception.ExcelAnalysisException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.jgw.supercodeplatform.common.pojo.common.JsonResult;
import com.jgw.supercodeplatform.exception.SuperCodeExtException;
import lombok.extern.slf4j.Slf4j;
import net.app315.hydra.example.common.enums.ErrorCodeEnum;
import net.app315.hydra.example.server.util.AppLogUtils;
import net.app315.nail.common.result.RichResult;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @author shixiongfei
 * @date 2020-02-17
 * @since
 */
@Primary
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常类增强，确保对客错误文案清晰
     *
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public RichResult<Void> handleConstraintViolationException(ConstraintViolationException ex) {
        RichResult<Void> richResult = new RichResult<Void>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        richResult.setMsg(message);
        richResult.setState(ErrorCodeEnum.PARAMS_ERROR.getHttpCode());
        richResult.setInternalErrorCode(ErrorCodeEnum.PARAMS_ERROR.getInternalErrorCode());
        AppLogUtils.error(log, message, ex);
        return richResult;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RichResult<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return handleArgumentError(ex);
    }

    @ExceptionHandler(BindException.class)
    public RichResult<Void> handleBindException(BindException ex) {
        return handleArgumentError(ex);
    }

    /**
     * 服务器内部错误异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(SuperCodeExtException.class)
    public RichResult handleSuperCodeExtException(SuperCodeExtException e) {
        log.warn("服务器内部错误", e);
        return new RichResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 处理参数错误
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2020-02-09
     * @updateDate 2020-02-09
     * @updatedBy shixiongfei
     */
    private RichResult<Void> handleArgumentError(Exception ex) {
        BindingResult result;
        if (ex instanceof BindException) {
            result = ((BindException) ex).getBindingResult();
        } else {
            result = ((MethodArgumentNotValidException) ex).getBindingResult();
        }

        RichResult<Void> richResult = new RichResult<Void>();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String message = error.getDefaultMessage();
        AppLogUtils.error(log, field + message, ex);
        richResult.setMsg(message);
        richResult.setState(ErrorCodeEnum.PARAMS_ERROR.getHttpCode());
        richResult.setInternalErrorCode(ErrorCodeEnum.PARAMS_ERROR.getInternalErrorCode());
        return richResult;
    }


    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    public JsonResult handleValidationException(ValidationException e) {
        log.warn("参数验证失败", e);
        JsonResult RestResult = new JsonResult(HttpStatus.BAD_REQUEST.value(), e.getCause().getLocalizedMessage() == null ? e.getMessage() : e.getCause().getLocalizedMessage(), null);
        return RestResult;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(InvalidFormatException.class)
    public JsonResult invalidFormatException(InvalidFormatException e) {
        log.warn("参数验证失败", e);
        JsonResult RestResult = new JsonResult(HttpStatus.BAD_REQUEST.value(), e.getCause().getLocalizedMessage() == null ? e.getMessage() : e.getCause().getLocalizedMessage(), null);
        return RestResult;
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.warn("不支持当前请求方法", e);
        JsonResult RestResult = new JsonResult(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), null);
        return RestResult;
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public JsonResult handleHttpMediaTypeNotSupportedException(Exception e) {
        log.warn("不支持当前媒体类型", e);
        JsonResult RestResult = new JsonResult(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(), null);
        return RestResult;
    }


    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.warn("缺少请求参数", e);
        JsonResult RestResult = new JsonResult();
        RestResult.setState(HttpStatus.BAD_REQUEST.value());
        RestResult.setMsg(e.getCause().getLocalizedMessage() == null ? e.getMessage() : e.getCause().getLocalizedMessage());
        return RestResult;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.warn("参数解析失败", e);
        JsonResult RestResult = new JsonResult();
        RestResult.setState(HttpStatus.BAD_REQUEST.value());
        RestResult.setMsg("could_not_read_json");
        return RestResult;
    }

    @ExceptionHandler
    public RichResult<Void> handleException(Throwable ex) {
        RichResult<Void> richResult = new RichResult<Void>();
        if (ex instanceof Exception) {
            richResult.setMsg(ErrorCodeEnum.SYSTEM_ERROR.getErrorMessage());
            richResult.setState(ErrorCodeEnum.SYSTEM_ERROR.getHttpCode());
            richResult.setInternalErrorCode(ErrorCodeEnum.SYSTEM_ERROR.getInternalErrorCode());
            AppLogUtils.error(log, "请求发生未知异常", ex);
        } else {
            richResult.setMsg(ErrorCodeEnum.SYSTEM_ERROR.getErrorMessage());
            richResult.setState(ErrorCodeEnum.SYSTEM_ERROR.getHttpCode());
            richResult.setInternalErrorCode(ErrorCodeEnum.SYSTEM_ERROR.getInternalErrorCode());
            AppLogUtils.error(log, "请求发生未知错误", ex);
        }
        return richResult;
    }

    @ExceptionHandler(ExcelAnalysisException.class)
    public RichResult<String> handleExcelAnalysisException(ExcelAnalysisException ex) {
        log.warn("excel导入错误", ex);
        return new RichResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}