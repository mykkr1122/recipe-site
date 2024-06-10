'use strict'
$(function () {
    $.getJSON("http://172.16.100.119:8080/recipe/searchTitle",
        function (data) {
            $('title').autocomplete({
                source: data,
                autoFocus: true,
                minLength: 1
            });
        }
    );
})