<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<a href="/admin/categories/add">Add category</a>

<!-- Hiển thị thông báo -->
<c:if test="${message != null}">
    <i>${message}</i>
</c:if>
<!-- Hết thông báo -->

<form action="/admin/categories/searchpaginated">
    <input type="text" name="name" id="name" placeholder="Nhập từ khóa để tìm" />
    <button type="submit">Search</button>
</form>

<!-- Size selection dropdown -->
<form action="/admin/categories/searchpaginated" method="get">
    <label for="size">Show:</label>
    <select name="size" id="size" onchange="this.form.submit()">
        <option value="3" ${categoryPage.size == 3 ? 'selected' : ''}>3</option>
        <option value="5" ${categoryPage.size == 5 ? 'selected' : ''}>5</option>
        <option value="10" ${categoryPage.size == 10 ? 'selected' : ''}>10</option>
        <option value="15" ${categoryPage.size == 15 ? 'selected' : ''}>15</option>
        <option value="20" ${categoryPage.size == 20 ? 'selected' : ''}>20</option>
    </select>
</form>

<c:if test="${!categoryPage.hasContent()}">
    No Category
</c:if>

<c:if test="${categoryPage.hasContent()}">
    <table>
        <tr>
            <th>STT</th>
            <th>Images</th>
            <th>Category name</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${categoryPage.content}" var="cate" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${cate.images}</td>
                <td>${cate.name}</td>
                <td>${cate.status}</td>
                <td>
                    <a href="/admin/categories/edit/${cate.id}">Sửa</a>
                    <a href="/admin/categories/delete/${cate.id}">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <!-- Pagination Links -->
    <c:if test="${categoryPage.totalPages > 0}">
        <ul class="pagination">
            <c:forEach items="${pageNumbers}" var="pageNumber">
                <li class="${pageNumber == categoryPage.number + 1 ? 'page-item active' : 'page-item'}">
                    <a href="<c:url value='/admin/categories/searchpaginated?name=${name}&size=${categoryPage.size}&page=${pageNumber}'/>">${pageNumber}</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</c:if>

