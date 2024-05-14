<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template pageTitle="Edit Profile">
  <div class="container">
    <div class="row justify-content-center align-items-center">
      <div class="col-md-6 text-center">
        <h3>Edit your profile</h3>
        <form method="POST" action="${pageContext.request.contextPath}/EditProfile">
          <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="" value="${user.username}" disabled>
          </div>
          <div class="form-group">
            <label for="nickname">Nickname</label>
            <input type="text" class="form-control" id="nickname" name="nickname" placeholder="" value="${user.nickname}">
          </div>
          <div class="form-group">
            <label for="firstname">First Name</label>
            <input type="text" class="form-control" id="firstname" name="firstname" placeholder="" value="${user.firstName}">
          </div>
          <div class="form-group">
            <label for="lastname">Last Name</label>
            <input type="text" class="form-control" id="lastname" name="lastname" placeholder="" value="${user.lastName}">
          </div>
          <div class="form-group">
            <label for="location">Location</label>
            <input type="text" class="form-control" id="location" name="location" placeholder="" value="${user.location}">
          </div>
          <div class="form-group">
            <label for="phone-number">Phone number</label>
            <input type="text" class="form-control" id="phone-number" name="phone-number" placeholder="" value="${user.phoneNumber}">
          </div>
          <div class="form-group">
            <label for="birthdate">Birth Date</label>
            <input type="date" class="form-control" id="birthdate" name="birthdate" placeholder="" value="${user.birthDate}">
          </div>
          <div class="form-group">
            <label for="gender">Gender</label>
            <select class="form-control" id="gender" name="gender">
              <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
              <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
              <option value="NonBinary" ${user.gender == 'NonBinary' ? 'selected' : ''}>NonBinary</option>
            </select>
          </div>
          <div class="form-group">
            <label for="bio">About yourself:</label>
            <textarea class="form-control" id="bio" name="bio" placeholder="" rows="3">${user.bio}</textarea>
          </div>
          <input type="hidden" name="username" value="${user.username}">
          <input type="submit" class="btn btn-primary" value="Submit Changes">
        </form>
      </div>
    </div>
  </div>
</t:template>
