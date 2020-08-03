function loadFile(event) {
    let output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
    output.onload = function() {
        URL.revokeObjectURL(output.src)
    }
}

function changePicture() {
    let image = $("#file-upload")[0].files[0];
    let file = new FormData();
    file.append('image', image);
    document.getElementById("productPicture").value = image.name;

    $.ajax({
        url: "/product/img-upload",
        type: "POST",
        data: file,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (res) {
            editProduct();
        },
        error: function (err) {
            console.error(err);
        }
    });
}

function addOption() {
    let random = Math.floor(Math.random() * 10);
    let table = document.getElementById("dataTable");
    let tableLength = table.rows.length;
    let skuName = document.getElementById("productName").value.slice(0, 3).toUpperCase();
    skuName = skuName + random + (tableLength + 1);

    table.insertRow(tableLength).outerHTML =
        `<tr>
                    <td><input type='text' class='skuValue form-control' value="` + skuName + `"></td>
                    <td>
                        <select class='optionSizeId form-control'>
                            <option value='1'>S</option>
                            <option value='2'>M</option>
                            <option value='3'>L</option>
                            <option value='4'>XL</option>
                        </select>
                    </td>
                    <td>
                        <select class='optionColorId form-control'>
                            <option value='5'>Black</option>
                            <option value='6'>White</option>
                            <option value='7'>Red</option>
                            <option value='8'>Blue</option>
                            <option value='9'>Yellow</option>
                            <option value='10'>Orange</option>
                        </select>
                    </td>
                    <td><input type='number' class='quantity form-control'/></td>
                    <td><button class="deleteButton btn btn-danger" onclick="deleteOption()">X</button></td>
                </tr>`;
}

function deleteOption() {
    $("#dataTable").on("click", ".deleteButton", function () {
        $(this).closest("tr").remove();
    });
}

function editProduct() {
    let listSkuId = document.getElementsByClassName('skuId');
    let listSkuValue = document.getElementsByClassName('skuValue');
    let listQuantity = document.getElementsByClassName('quantity');
    let listOptionSizeId = document.getElementsByClassName('optionSizeId');
    let listOptionColorId = document.getElementsByClassName('optionColorId');
    let jsonList = [];

    for (let i = 0; i < listSkuValue.length; i++) {
        let jsonText = {
            "skuId": $(listSkuId[i]).val(),
            "skuValue": $(listSkuValue[i]).val(),
            "quantity": $(listQuantity[i]).val(),
            "options": [
                {
                    "optionId": $(listOptionSizeId[i]).val()
                },
                {
                    "optionId": $(listOptionColorId[i]).val()
                }
            ]
        }
        jsonList.push(jsonText)
    }

    let productId = $("#productId").val();
    let productName = $("#productName").val();
    let categoryId = $("#categoryId").val();
    let detail = $("#detail").val();
    let price = $("#price").val();
    let picture = $("#productPicture").val();

    let json = {
        "productId": productId,
        "productName": productName,
        "detail": detail,
        "price": price,
        "skuList": jsonList,
        "category": {
            "categoryId": categoryId
        },
        "picture": picture,
    };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(json),
        url: '/product/edit/' + productId,
        success: function () {
            document.getElementById("message").innerHTML = "Product updated!"
        }
    });
    event.preventDefault();
}