<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script src="https://www.gstatic.com/firebasejs/4.8.1/firebase.js"></script>
<script src="https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js"></script>
<script src="../firebase-messaging-sw.js"></script>
<input type="button" value="init" onclick="fireBaseInit()">
<input type="button" value="get token & send to server" onclick="getTokenAndSendToServer()">
<script>
    function fireBaseInit(){
        const config = {
            apiKey: "AIzaSyDJRyfHWN39S6cDxyOvdWZq7Q-pwvvUzVM",
            authDomain: "symbolic-rope-192608.firebaseapp.com",
            databaseURL: "https://symbolic-rope-192608.firebaseio.com",
            projectId: "symbolic-rope-192608",
            storageBucket: "",
            messagingSenderId: "642363583826"
        };
        firebase.initializeApp(config);
    }

    function getTokenAndSendToServer(){
        const messaging = firebase.messaging();
        messaging.requestPermission().then(function () {
            console.log('Notification permission granted.');
        }).catch(function (err) {
            console.log('Unable to get permission to notify.', err);
        });

        messaging.getToken().then(function(currentToken) {
            if (currentToken) {
                sendTokenToServer(currentToken);
            } else {
                console.log('No Instance ID token available. Request permission to generate one.');
            }
        }).catch(function(err) {
            console.log('An error occurred while retrieving token. ', err);
        });

        messaging.onMessage(function (payload) {
            console.log('Message received. ', payload);
            /*sendTokenToServer(payload);*/
        });
    }

    function sendTokenToServer(currentToken) {
        location.href='/send/push?token=' + currentToken;
    }
</script>
