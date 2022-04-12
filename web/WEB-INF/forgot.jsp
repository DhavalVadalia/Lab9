<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>Forgot Password Page</h1>
        <form action="forgot" method="post">
            <table> <tr> <td>
                        Please enter your email </td> </tr>
                <tr> <td> 
                        Email Address: <input type="text" name="email"></td> </tr>
                <tr> <td> <input type="submit" value="Submit"> </td> </tr> </table>
        </form>
        <p>${msg}</p>
    </body>
</html>
