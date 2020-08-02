function deleteProduct() {
    let productId = $("#productId").val();

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "DELETE",
        url: '/product/delete/' + productId,
        success: function () {
            document.getElementById("message").innerHTML = "Product deleted!"
        }
    });
    event.preventDefault();
}