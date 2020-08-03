document.getElementById('range-picker').addEventListener('click', function(e) {
    var sizeList = document.getElementById('range-picker').children;
    for (var i = 0; i <= sizeList.length - 1; i++) {
        if (sizeList[i].classList.contains('active')) {
            sizeList[i].classList.remove('active');
        }
    }
    e.target.classList.add('active');
});

document.getElementById('color-picker').addEventListener('click', function(e) {
    var colorList = document.getElementById('color-picker').children;
    for (var i = 0; i <= colorList.length - 1; i++) {
        if (colorList[i].classList.contains('active')) {
            colorList[i].classList.remove('active');
        }
    }
    e.target.classList.add('active');
});

let sizeId = 0;
let colorId = 0;

$(".set-size").click(function() {
    let size = $(this).text();
    switch (size) {
        case "S":
            size = 1;
            break;
        case "M":
            size = 2;
            break
        case "L":
            size = 3;
            break;
        case "XL":
            size = 4;
            break
    }
    sizeId = size;
});

$(".set-color").click(function() {
    colorId = $(this).text();
});

function addToCart() {
    let productId = $('#productId').text();
    if (colorId === 0 || sizeId === 0) {
        console.log("Add option");
    } else {
        $.ajax({
            type: 'GET',
            url: '/cart/addtocart/' + productId + '/' + sizeId + '/' + colorId,
            success: function () {
                console.log(productId, sizeId, colorId)
            }
        })
        event.preventDefault();
    }
}