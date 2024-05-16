<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template pageTitle="Checkout">
  <div class="row">
    <div class="col-sm-6">
      <h1>Checkout, ${cart.user.username}</h1>

      <c:if test="${empty games}">
        <p>You don't have any games in your cart.</p>
      </c:if>

      <c:if test="${not empty games}">
        <div id="games">
          <c:forEach var="game" items="${games}">
            <div class="card">
              <div class="card-body">
                <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">${game.gameName}</a>
                <c:choose>
                  <c:when test="${gamePrices[game.gameId][1] > 0}">
                    <p style="color: grey; text-decoration: line-through;">Price: $<c:out value="${gamePrices[game.gameId][0]}" /></p>
                    <p>Discount: <fmt:formatNumber value="${gamePrices[game.gameId][2]}" pattern="##"/>%</p>
                    <p>Discounted Price: $<c:out value="${gamePrices[game.gameId][1]}" /></p>
                  </c:when>
                  <c:otherwise>
                    <c:choose>
                      <c:when test="${gamePrices[game.gameId][0] == 0}">
                        <p>Price: Free</p>
                      </c:when>
                      <c:otherwise>
                        <c:set var="price" value="${gamePrices[game.gameId][0]}" />
                        <c:choose>
                          <c:when test="${price == price.intValue()}">
                            <p>Price: $<c:out value="${price.intValue()}" /></p>
                          </c:when>
                          <c:otherwise>
                            <p>Price: $<c:out value="${price}" /></p>
                          </c:otherwise>
                        </c:choose>
                      </c:otherwise>
                    </c:choose>
                  </c:otherwise>
                </c:choose>
              </div>
            </div>
          </c:forEach>
        </div>
        <p>Total Price: $${totalPrice}</p>

        <form id="paymentForm" action="${pageContext.request.contextPath}/Payment" method="post">
          <!-- Card details -->
          <label for="savedCards">Cards:</label>
          <select id="savedCards" name="savedCards">
            <option value="">Select card</option>
            <c:forEach var="card" items="${cardDetails}">
              <option value="${card.cardId}" data-card-number="${card.cardNumber}" data-expiration-date="${card.expirationDate}">${card.cardName}</option>
            </c:forEach>

            <option value="" disabled>───────────</option>
            <option value="">New Card</option>
          </select>

          <div>
            <label for="cardName">Card Name:</label>
            <input type="text" id="cardName" name="cardName" required><br>

            <label for="cardNumber">Card Number:</label>
            <input type="text" id="cardNumber" name="cardNumber" required><br>

            <label for="expirationDate">Expiration Date:</label>
            <input type="text" id="expirationDate" name="expirationDate" pattern="(0[1-9]|1[0-2])\/\d{2}" placeholder="MM/YY" required><br>

            <label for="cvv">CVV:</label>
            <input type="text" id="cvv" name="cvv" required><br>
            <!-- Checkbox for saving card details -->
            <input type="checkbox" id="saveCardDetails" name="saveCardDetails">
            <label for="saveCardDetails">Save card details for future purchases</label><br>
          </div>
          <input type="hidden" name="username" value="${cart.user.username}">
          <input type="hidden" name="cart" value="${cart.cartId}">
          <input type="hidden" name="totalPrice" value="${cart.totalPrice}">
          <button type="submit" class="btn btn-primary">Pay Now</button>
        </form>
      </c:if>
    </div>
  </div>
</t:template>

<script>
  document.addEventListener('DOMContentLoaded', function() {
    var paymentForm = document.getElementById('paymentForm');
    var savedCardsSelect = document.getElementById('savedCards');
    var cardNameInput = document.getElementById('cardName');
    var cardNumberInput = document.getElementById('cardNumber');
    var expirationDateInput = document.getElementById('expirationDate');
    var cvvInput = document.getElementById('cvv');


    // Funcție pentru a umple câmpurile formularului cu detaliile cardului selectat
    function fillFormFields(card) {
      document.getElementById('cardName').value = card.cardName;
      document.getElementById('cardNumber').value = card.cardNumber;
      document.getElementById('expirationDate').value = card.expirationDate;
    }
    // Eveniment pentru schimbarea selecției cardului salvat
    savedCardsSelect.addEventListener('change', function() {
      var selectedIndex = this.selectedIndex;
      var selectedOption = this.options[selectedIndex];

      if (selectedIndex !== 0 && selectedOption.value !== "") {
        // Obțineți datele cardului selectat
        var card = {
          cardName: selectedOption.textContent,
          cardNumber: selectedOption.getAttribute('data-card-number'),
          expirationDate: selectedOption.getAttribute('data-expiration-date')
        };
        // Umpleți câmpurile formularului cu detaliile cardului selectat
        fillFormFields(card);

        // Dezactivați câmpurile de introducere a datelor cardului
        cardNameInput.readOnly = true;
        cardNumberInput.readOnly = true;
        expirationDateInput.readOnly = true;
      } else {

        cardNameInput.value = '';
        cardNumberInput.value = '';
        expirationDateInput.value = '';
        cardNameInput.readOnly = false;
        cardNumberInput.readOnly = false;
        expirationDateInput.readOnly = false;
      }
    });

    function validateCardDetails() {
      var cardNumber = cardNumberInput.value.replace(/\s+/g, '');
      var expirationDate = expirationDateInput.value;
      var cvv = cvvInput.value;

      if (cardNumber.length !== 16) {
        alert('Please enter a valid 16-digit card number.');
        return false;
      }

      var expirationDatePattern = /^(0[1-9]|1[0-2])\/\d{2}$/;
      if (!expirationDate.match(expirationDatePattern)) {
        alert('Please enter a valid expiration date in MM/YY format.');
        return false;
      }

      if (cvv.length !== 3) {
        alert('Please enter a valid 3-digit CVV.');
        return false;
      }

      return true;
    }



    paymentForm.addEventListener('submit', function(event) {

      var selectedIndex = savedCardsSelect.selectedIndex;
      if (selectedIndex !== 0) {

        var selectedOption = savedCardsSelect.options[selectedIndex];
        var cardId = selectedOption.value;

        var hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'selectedCardId';
        hiddenInput.value = cardId;
        this.appendChild(hiddenInput);
      }


      if (!validateCardDetails()) {
        event.preventDefault();
      }
    });

    cardNumberInput.addEventListener('input', function() {
      var trimmedValue = this.value.replace(/\s+/g, '');
      var formattedValue = '';
      for (var i = 0; i < trimmedValue.length; i++) {
        if (i > 0 && i % 4 === 0) {
          formattedValue += ' ';
        }
        formattedValue += trimmedValue.charAt(i);
      }
      formattedValue = formattedValue.slice(0, 19);
      this.value = formattedValue;
    });


    expirationDateInput.addEventListener('input', function() {
      var value = this.value.replace(/\D/g, '');
      if (value.length > 4) {
        value = value.slice(0, 4);
      }
      var formattedValue = '';
      for (var i = 0; i < value.length; i++) {
        if (i === 2 && value.length > 2) {
          formattedValue += '/';
        }
        formattedValue += value.charAt(i);
      }
      this.value = formattedValue;
    });

  });
</script>
