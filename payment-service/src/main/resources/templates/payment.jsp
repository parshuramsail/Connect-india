<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>-->
<!DOCTYPE html>
<html>
<head>
<style>
body, html {
	height: 100%;
	padding: 0;
	margin: 0;
}

h1 {
	font-weight: normal;
	font-size: 4em;
	margin: 0 auto;
	margin-top: 30px;
	width: 500px;
	color: #F90;
	text-align: center;
}

}
.fish {
	background-image:
		url('http://www.geertjanhendriks.nl/codepen/form/fish.png');
	width: 235px;
	height: 104px;
	margin-left: -235px;
	position: absolute;
	animation: myfirst 24s;
	-webkit-animation: myfirst 24s;
	animation-iteration-count: infinite;
	-webkit-animation-iteration-count: infinite;
	animation-timing-function: linear;
	-webkit-animation-timing-function: linear;
}

#fish {
	top: 120px;
}

#fish2 {
	top: 260px;
	animation-delay: 12s;
	-webkit-animation-delay: 12s;
}

header {
	height: 160px;
	background: url('http://www.geertjanhendriks.nl/codepen/form/golf.png')
		repeat-x bottom;
}

#form {
	height: 100%;
	background-color: #98d4f3;
	overflow: hidden;
	position: relative;
}

form {
	margin: 0 auto;
	width: 500px;
	padding-top: 40px;
	color: white;
	position: relative;
}

label, input, textarea {
	display: block;
}

input, textarea {
	width: 500px;
	border: none;
	border-radius: 20px;
	outline: none;
	padding: 10px;
	font-size: 1em;
	color: #676767;
	transition: border 0.5s;
	-webkit-transition: border 0.5s;
	-moz-transition: border 0.5s;
	-o-transition: border 0.5s;
	border: solid 3px #98d4f3;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

input:focus, textarea:focus {
	border: solid 3px #77bde0;
}

textarea {
	height: 100px;
	resize: none;
	overflow: auto;
}

input[type="submit"] {
	background-color: #F90;
	color: white;
	height: 50px;
	cursor: pointer;
	margin-top: 30px;
	font-size: 1.29em;
	-webkit-transition: background-color 0.5s;
	-moz-transition: background-color 0.5s;
	-o-transition: background-color 0.5s;
	transition: background-color 0.5s;
}

.margin-left {
	margin-left: 300px;
	margin-top: 100px;
}

input[type="submit"]:hover {
	background-color: #e58f0e;
}

label {
	font-size: 1.5em;
	margin-top: 20px;
	padding-left: 20px;
}

.formgroup, .formgroup-active, .formgroup-error {
	background-repeat: no-repeat;
	background-position: right bottom;
	background-size: 10.5%;
	transition: background-image 0.7s;
	-webkit-transition: background-image 0.7s;
	-moz-transition: background-image 0.7s;
	-o-transition: background-image 0.7s;
	width: 566px;
	padding-top: 2px;
}

.formgroup {
	background-image:
		url('http://www.geertjanhendriks.nl/codepen/form/pixel.gif');
}

.formgroup-active {
	background-image:
		url('http://www.geertjanhendriks.nl/codepen/form/octo.png');
}

.formgroup-error {
	background-image:
		url('http://www.geertjanhendriks.nl/codepen/form/octo-error.png');
	color: red;
}
</style>
<meta charset="UTF-8">
<title>Payment</title>
</head>
<body>
	<div id="form">

		<form action="payment" method="POST" id="form">

			<div class="formgroup" id="email-form">
				<label for="email">EMAIL</label> <input type="email" id="email"
					name="email" />
			</div>

			<div class="formgroup" id="phone">
				<label for="phoneNumber">PHONE</label> 
				<input type="text" id="phoneNumber" name="phoneNumber" />
			</div>

			<div class="formgroup" id="amountDiv">
				<label for="amount">AMOUNT</label> 
				<input type="text" id="amount" name="amount" />
			</div>
			<input type="submit" id="rzp-button1" value="PAY NOW" />

		</form>
	</div>

</body>

<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script>
	var form = document.getElementById('form');

	form.addEventListener('submit', function(e) {
		e.preventDefault();

		var mail = document.getElementById('email').value;
		var phone = document.getElementById('phoneNumber').value;
		var amount = parseInt(document.getElementById('amount').value);
		//var amount = 100;
		let orderID = 0;
		let applicationFee = 0;

		const data = {
			"email" : mail,
			"phoneNumber" : phone,
			"amount" : parseInt(amount)
		};
		
		console.log(data);

		// POST request
		fetch("http://localhost:8081/payments/api/v/payment", {

			method : 'POST',
			body : JSON.stringify(data),
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function(response) {
			return response.json();
		}).then(function(data) {
			orderID = data.razorpayOrderId;
			paymentID = data.PaymentId;
			applicationFee = data.aplicationFee;

			var options = {
				"key" : "rzp_test_W4YRGR2MY42TcG", // Enter the Key ID generated from the Dashboard
				"amount" : 700, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
				"currency" : "INR",
				"name" : "Green Eclips",
				"description" : "Test Transaction",
				"image" : "https://example.com/your_logo",
				"order_id" : orderID, //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
				"payment_id":paymentID,
				"callback_url" : "https://eneqd3r9zrjok.x.pipedream.net/",
				"prefill" : {
					"name" : "Name",
					"email" : mail,
					"contact" : phone
				},
				"notes" : {
					"address" : "Razorpay Corporate Office"
				},
				"theme" : {
					"color" : "#3399cc"
				}
			};

			var rzp1 = new Razorpay(options);

			console.log(options);

			rzp1.open();
			e.preventDefault();

			// Write the code here for updating the status using put method

		})
	})
</script>