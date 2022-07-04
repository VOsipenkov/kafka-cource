Test request

POST localhost:8080/order/send
Content-Type: application/json

{
"deliveryName": "deliveryName1",
"deliveryStreet": "deliveryStreet1",
"deliveryCity": "deliveryCity1",
"deliveryState": "deliveryState1",
"deliveryZip": "deliveryZip1",
"ccNumber": "ccNumber1",
"ccExpiration": "ccExpiration1",
"ccCvv": "123"
}
