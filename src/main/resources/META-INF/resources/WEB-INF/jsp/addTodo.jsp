
    <%@include file="commons/header.jspf" %>
    <%@include file="commons/navigation.jspf" %>

    <div class="container">
            <%--@elvariable id="todo" type="org.algorhythm.todo.todoApp.Todo"--%>
            <form:form method="post" modelAttribute="todo">
                <h1>The todo app</h1>
                <label for="description">Description</label> <form:input id="description" class="input-group-text" name="description" type="text"  path="description"></form:input>
                <form:errors path="description" cssClass="text-warning"></form:errors>
                <hr>
                <label for="targetDate">Target Date</label> <form:input id="targetDate" class="input-group-text" name="targetDate" type="date"  path="targetDate"></form:input>
                <form:errors path="targetDate" cssClass="text-warning"></form:errors>
                <hr>

                <label for="done">Is done?</label>
                <div class="form-check form-switch">
                    <label class="form-check-label" for="done">Done?</label>
                    <input class="form-check-input font-size-large" type="checkbox" id="done" name="done" path="done">
                </div>
                <hr>

                <button class="btn btn-success" type="submit">Submit</button>
                <c:if test="${error.equals(null)}">
                    <div class="alert-danger alert">${error}</div>
                </c:if>
            </form:form>
        </div>
    <%@include file="commons/footer.jspf" %>
