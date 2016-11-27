<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="lightbox" style="display:block;">
    <div class="lb-wrap">
        <a href="#" class="close">x</a>
        <div class="lb-content">
            <form id="loginForm" name="loginForm" method="POST" action="controller">
                <h1>Log in</h1>
                <div class="f-item">
                    <label for="login">${header_login}</label>
                    <input type="text" id="login" name="login" autofocus required>
                </div>
                <div class="f-item">
                    <label for="password">${header_password}</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="f-item checkbox">
                    <input type="checkbox" id="remember_me" name="checkbox" />
                    <label for="remember_me">Remember me next time</label>
                </div>
                <p>
                    <a href="controller?command=goToRemindPassword" title="Forgot password?">
                        ${header_forgotPassword}
                    </a><br/>
                    Dont have an account yet?
                    <a href="controller?command=goToRegistration" title="Sign up">
                        ${header_register}
                    </a>
                </p>
                <input type="submit" id="signIn" value="${header_signIn}" class="gradient-button">
            </form>
        </div>
    </div>
</div>

