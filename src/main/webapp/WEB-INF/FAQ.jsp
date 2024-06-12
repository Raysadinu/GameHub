<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template pageTitle="FAQ">

    <head>
        <style>
            body {
                font-family: Arial, sans-serif;
                line-height: 1.6;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            h2 {
                color: #333;
                text-align: center;
                margin-top: 20px;
            }
            .faq-container {
                width: 80%;
                margin: 20px auto;
                background: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .faq-section {
                margin-bottom: 20px;
            }
            .faq-section h3 {
                color: #555;
                border-bottom: 2px solid #ddd;
                padding-bottom: 10px;
            }
            .faq-question {
                margin: 10px 0;
            }
            .faq-question h4 {
                color: #333;
            }
            .faq-question p {
                color: #666;
                background: #f9f9f9;
                padding: 10px;
                border-radius: 5px;
                box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>

    <h2 style="margin: 100px"> FAQ: Answers to Frequently Asked Questions</h2>

    <div class="faq-container">
        <div class="faq-section">
            <h3>Filters</h3>
            <div class="faq-question">
                <h4>How do the filters work?</h4>
                <p>The filters allow you to narrow down your search results to find games that match specific criteria. You can select one or more categories to refine your search.</p>
            </div>
            <div class="faq-question">
                <h4>Can I select multiple categories?</h4>
                <p>Yes, you can select multiple categories. The more categories you select, the more specific your search results will be. The filters will show only the games that match all the selected categories.</p>
            </div>
            <div class="faq-question">
                <h4>Can I filter by both free and discount games?</h4>
                <p>No, you cannot filter by both free and discount games at the same time. You can choose either free games or discounted games, but not both.</p>
            </div>
            <div class="faq-question">
                <h4>Can I filter free games by price range?</h4>
                <p>No, if you have selected the free games filter, you cannot filter by price range. The price range filter is only applicable to paid games.</p>
            </div>
        </div>

        <div class="faq-section">
            <h3> Purchasing Games</h3>
            <div class="faq-question">
                <h4> How does purchasing a game work on GameHub? </h4>
                <p> The process of buying a game is very simple! You have to go on our store page and add the game you want to buy to your cart. Once the game is added
                    to your cart you can proceed to checkout from the cart page. During the checkout you will have to fill in your payment details and the game will be yours once
                    the purchase is complete.</p>
            </div>
            <div class="faq-question">
                <h4> Where can I see my purchased games? </h4>
                <p> Your purchased games will be automatically added to your library.</p>
            </div>
            <div class="faq-question">
                <h4> Can I get a refund on a game? </h4>
                <p> Unfortunately, refunds are not available for the games you purchase. The reason behind this is that the games are sold as digital copies which can be easily
                    stolen, so because we want to respect the rights of the developers, we do not offer refunds.</p>
            </div>
            <div class="faq-question">
                <h4> What if I don't see the game in my library after I purchased it? </h4>
                <p> Sometimes, payments take a while to process, so just be patient, your game will be automatically added to your library once the payment has been successfully completed.</p>
            </div>
        </div>

        <div class="faq-section">
            <h3> Community </h3>
            <div class="faq-question">
                <h4> What is the community and how does it work? </h4>
                <p> The community is a special feature that allow users to talk about all the games they love. On the community tab you will be able to find information about
                    the games you love, everything will be available on this tab from guides to memes to just chatting about games.</p>
            </div>
            <div class="faq-question">
                <h4> Who can post in the community? </h4>
                <p> Everyone who has an account on GameHub. </p>
            </div>
        </div>
    </div>

</t:template>
