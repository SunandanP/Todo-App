
        <%@include file="commons/navigation.jspf" %>
        <%@include file="commons/header.jspf" %>
        <div class="container font-size-large">
            <div class="text-center font-size-large" style="margin: 20px">
                <h1>Your todos</h1>
                <hr>
            </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Description</th>
                        <th scope="col">Target Date</th>
                        <th scope="col">Done?</th>
                        <th colspan="3">Actions</th>
                    </tr>
                    </thead>
                    <c:forEach items="${todos}" var="todo">
                        <tbody class="table-striped">
                        <tr>
                            <td>${todo.description}</td>
                            <td>${todo.targetDate}</td>
                            <td>${todo.done}</td>
                            <td><a href="update-todo?id=${todo.id}" class="btn btn-warning">Update</a>
                            <a href="delete-todo?id=${todo.id}" class="btn btn-danger">Delete</a></td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            <a class="btn btn-success" href="add-todo">Add todo</a>
        </div>
        <%@include file="commons/footer.jspf" %>
