<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Result</title>
</head>

<body>

<div class="body">
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div>
        <table>
            <thead>
            <tr>
                <td>Id</td>
                <td>Data</td>
                <td>Date</td>
            </tr>
            </thead>
            <tbody>
            <g:each in="${all}" var="data">
                <tr>
                    <td>${data.id}</td>
                    <td>${data.data}</td>
                    <td>${data.recordCreatedDate}</td>
                </tr>
            </g:each>
            </tbody>
        </table>

    </div>

    <div class="buttons">
        <g:link controller="yahoo" action="yahoo">Start again</g:link>
    </div>

    <div class="buttons">
        <g:form action="yahoo">
            <g:submitButton name="endFlow" value="I'm done"></g:submitButton>
        </g:form>
    </div>
</div>
</body>
</html>