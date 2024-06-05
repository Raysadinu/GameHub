<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:checkout pageTitle="Checkout">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

  <div class="container">
    <h1 class="text-center">Checkout</h1>

      <c:choose>
        <c:when test="${empty games}">
          <p class="text-center">You don't have any games in your cart.</p>
        </c:when>
        <c:otherwise>
          <div class="game-container">
            <c:forEach var="game" items="${games}">
              <div class="game-card">
                <div class="card-body">
                  <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">
                    <img src="${pageContext.request.contextPath}/GamePhotos?gameId=${game.gameId}" alt="Game Profile Picture" class="game-picture">
                  </a>
                  <div>
                    <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">${game.gameName}</a>
                    <c:choose>
                      <c:when test="${gamePrices[game.gameId][0] == 0}">
                        <p>Free</p>
                      </c:when>
                      <c:when test="${gamePrices[game.gameId][1] > 0}">
                        <div class="price-container">
                          <b class="price-discount"><fmt:formatNumber value="${gamePrices[game.gameId][2]}" pattern="##"/>%</b>
                          <div class="price-values">
                            <div class="old-price">$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></div>
                            <div class="new-price">$<fmt:formatNumber value="${gamePrices[game.gameId][1]}" pattern="##0.00"/></div>
                          </div>
                        </div>
                      </c:when>
                      <c:otherwise>
                        <p>$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
                      </c:otherwise>
                    </c:choose>
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>
          <p class="total-price">Total Price: $${totalPrice}</p>
          <div class="payment-container">
            <div class="payment-card">
              <form id="paymentForm" action="${pageContext.request.contextPath}/Payment" method="post">
                <div class="form-group">
                  <label for="savedCards" class="text-center">Select Card:</label>
                  <select id="savedCards" name="savedCards" class="form-control">
                    <option value="">Select card</option>
                    <c:forEach var="card" items="${cardDetails}">
                      <option value="${card.cardId}" data-card-number="${card.cardNumber}" data-expiration-date="${card.expirationDate}">${card.cardName}</option>
                    </c:forEach>
                    <option value="" disabled>───────────</option>
                    <option value="">New Card</option>
                  </select>
                </div>

                <div class="form-group input-group">
                  <label for="cardName"><i class="fas fa-id-card"></i> </label>
                  <input type="text" id="cardName" name="cardName" class="form-control" placeholder="Card Owner Name" required>
                </div>

                <div class="form-group input-group">
                  <label for="cardNumber"><i class="fas fa-credit-card"></i> </label>
                  <input type="text" id="cardNumber" name="cardNumber" class="form-control" placeholder="Valid card number" required maxlength="19">
                </div>

                <div class="form-group row">
                  <div class="col-md-6 input-group">
                    <label for="expirationDate"><i class="fas fa-calendar-alt"></i> </label>
                    <input type="text" id="expirationDate" name="expirationDate" pattern="(0[1-9]|1[0-2])\/\d{2}" class="form-control" placeholder="MM/YY" required maxlength="5">
                  </div>
                  <div class="col-md-6 input-group">
                    <label for="cvv"><i class="fas fa-lock"></i> </label>
                    <input type="text" id="cvv" name="cvv" class="form-control" placeholder="CVV" required maxlength="3">
                  </div>
                </div>

                <div class="form-check">
                  <input type="checkbox" id="saveCardDetails" name="saveCardDetails" class="form-check-input">
                  <label for="saveCardDetails" class="form-check-label">Save card details for future purchases</label>
                </div>

                <input type="hidden" name="username" value="${cart.user.username}">
                <input type="hidden" name="cart" value="${cart.cartId}">
                <input type="hidden" name="totalPrice" value="${cart.totalPrice}">
                <button type="submit" class="btn btn-primary btn-block">Confirm Payment <i class="fas fa-credit-card"></i></button>
              </form>
            </div>
          </div>
      </c:otherwise>
    </c:choose>
  </div>
</t:checkout>


<script>
  document.addEventListener('DOMContentLoaded', function() {
    var paymentForm = document.getElementById('paymentForm');
    var savedCardsSelect = document.getElementById('savedCards');
    var cardNameInput = document.getElementById('cardName');
    var cardNumberInput = document.getElementById('cardNumber');
    var expirationDateInput = document.getElementById('expirationDate');
    var cvvInput = document.getElementById('cvv');

    function fillFormFields(card) {
      cardNameInput.value = card.cardName;
      cardNumberInput.value = card.cardNumber;
      expirationDateInput.value = card.expirationDate;
    }

    savedCardsSelect.addEventListener('change', function() {
      var selectedIndex = this.selectedIndex;
      var selectedOption = this.options[selectedIndex];

      if (selectedIndex !== 0 && selectedOption.value !== "") {
        var card = {
          cardName: selectedOption.textContent,
          cardNumber: selectedOption.getAttribute('data-card-number'),
          expirationDate: selectedOption.getAttribute('data-expiration-date')
        };
        fillFormFields(card);
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