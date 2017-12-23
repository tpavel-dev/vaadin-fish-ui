package kz.kcell.apps.common.exceptions;

import org.junit.Test;


/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
public class CodeExceptionTests {

    @Test(expected = SampleBusinessCodeException.class)
    public void test1() throws BusinessException {
        try {
            throw new SampleBusinessCodeException(SampleBusinessExceptionCodes.exc1, "потому что, потому что 1");
        } catch (java.lang.Exception exc) {
            exc.printStackTrace();
            throw exc;
        }
    }

    @Test(expected = SampleBusinessCodeException.class)
    public void test2() throws BusinessException {
        try {
            throw new SampleBusinessCodeException(SampleBusinessExceptionCodes.exc2, "потому что, потому что 2");
        } catch (java.lang.Exception exc) {
            exc.printStackTrace();
            throw exc;
        }
    }

    @Test(expected = NotAuthException.class)
    public void testNotAuthException() throws BaseException {
        try {
            throw new NotAuthException();
        } catch (java.lang.Exception exc) {
            exc.printStackTrace();
            throw exc;
        }
    }

    @Test(expected = NotAuthorizedException.class)
    public void testNotAuthorizedException() throws BaseException {

        try {
            throw new NotAuthorizedException();
        } catch (java.lang.Exception exc) {
            exc.printStackTrace();
            throw exc;
        }
    }

    @Test(expected = NotAuthenticatedException.class)
    public void testNotAuthenticatedException() throws BaseException {

        try {
            throw new NotAuthenticatedException();
        } catch (java.lang.Exception exc) {
            exc.printStackTrace();
            throw exc;
        }


    }
}
