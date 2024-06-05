<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template pageTitle="Edit Profile">

  <div class="container">
    <div class="row justify-content-center align-items-center">
      <div class="col-md-6 text-center">
        <h3>Edit your profile, ${user.username}</h3>
        <hr class="mb-4">
        <form method="POST" action="${pageContext.request.contextPath}/EditProfile" enctype="multipart/form-data">
          <div class="row">
            <div class="form-group">
              <label for="profilePicture">Profile Picture</label><br>
              <img src="data:image/jpeg;base64,${user.profilePicture}" alt="Profile Picture" width="150" height="150"><br>
              <input type="file" class="form-control" id="profilePicture" name="profilePicture" accept="image/*">
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label for="firstname">First Name *</label>
              <input type="text" class="form-control" id="firstname" name="firstname" placeholder="First Name" value="${user.firstName}" required>
            </div>
            <div class="form-group">
              <label for="lastname">Last Name *</label>
              <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Last Name" value="${user.lastName}" required>
            </div>
            <div class="form-group">
              <label for="nickname">Nickname</label>
              <input type="text" class="form-control" id="nickname" name="nickname" placeholder="Nickname" value="${user.nickname}">
            </div>
          </div>
          <div class="row">
            <div class="form-group">
              <label for="location">Location</label>
              <input type="text" class="form-control" id="location" name="location" placeholder="Location" value="${user.location}">
            </div>
            <div class="form-group">
              <label for="phone-number">Phone number</label>
              <input type="text" class="form-control" id="phone-number" name="phone-number" placeholder="Phone Number" value="${user.phoneNumber}">
            </div>
            <div class="form-group">
              <label for="birthdate">Birth Date *</label>
              <input type="date" class="form-control" id="birthdate" name="birthdate" placeholder="Birth date" value="${user.birthDate}" required ${empty user.birthDate ? '' : 'readonly'}>
            </div>

            <div class="form-group">
              <label for="gender">Gender *</label>
              <select class="form-control" id="gender" name="gender" required>
                <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
                <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
                <option value="NonBinary" ${user.gender == 'NonBinary' ? 'selected' : ''}>NonBinary</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="bio">About yourself:</label>
            <textarea class="form-control" id="bio" name="bio" placeholder="Tell us about yourself" rows="3">${user.bio}</textarea>
          </div>

          <input type="hidden" name="username" value="${user.username}">
          <input type="hidden" id="imageFormat" name="imageFormat">
          <input type="submit" class="btn btn-primary" value="Submit Changes">
        </form>
      </div>
    </div>
  </div>
</t:template>
<script>
  // Function to extract image format and set the hidden input value
  function setImageFormat() {
    var input = document.getElementById('profilePicture');
    if (input.files && input.files[0]) {
      var fileName = input.files[0].name;
      var imageFormat = fileName.split('.').pop().toLowerCase();
      document.getElementById('imageFormat').value = imageFormat;
    }
  }
  document.getElementById('profilePicture').addEventListener('change', setImageFormat);
</script>
