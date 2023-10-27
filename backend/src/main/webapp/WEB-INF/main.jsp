<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h1>Welcome to the Online Storefront</h1>
<a href="${pageContext.request.contextPath}/products">View All Products</a>
<a href="${pageContext.request.contextPath}/cart">View Cart</a>

<form action="${pageContext.request.contextPath}/staff-auth" method="post">
    <input type="password" name="passcode" placeholder="Enter Staff Passcode">
    <button type="submit">Authenticate as Staff</button>
</form>

<!-- For staff users only, add a link to create new products -->
<% if (request.isUserInRole("staff")) { %>
<a href="${pageContext.request.contextPath}/create-product">Create New Product</a>
<% } %>
</body>
</html>
