<!DOCTYPE html>
<html>
<head>
    <title>security</title>
    <meta charset="utf-8"/>
</head>
<body>
<div>
        <#if loginError?? && loginError>
            <p class="error">Wrong user or password</p>
        </#if>
    <form action="/login" method="post">
        <input type="text" name="username" title="用户名"/>
        <input type="password" name="password" title="密码"/>
        <input type="submit" value="登录">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
</div>
</body>
</html>