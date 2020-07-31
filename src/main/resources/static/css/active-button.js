$(".textbox input").focusout(function () {
    if ($(this).val() == "") {
        $(this).siblings().removeClass("hidden");
    } else {
        $(this).siblings().addClass("hidden");
    }
});

$(".textbox input").keyup(function () {
    var inputs = $(".textbox input");
    if (inputs[0].value != "" && inputs[1].value) {
        $(".signIn-btn").attr("disabled", false);
        $(".signIn-btn").addClass("active");
    } else {
        $(".signIn-btn").attr("disabled", true);
        $(".signIn-btn").removeClass("active")
    }
});

$(".textbox input").keyup(function () {
    var inputs = $(".textbox input");
    if (inputs[0].value != "" && inputs[1].value && inputs[2].value) {
        $(".signUp-btn").attr("disabled", false);
        $(".signUp-btn").addClass("active");
    } else {
        $(".signUp-btn").attr("disabled", true);
        $(".signUp-btn").removeClass("active")
    }
});