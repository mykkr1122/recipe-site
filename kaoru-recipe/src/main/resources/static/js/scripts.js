
/**
 * 画像をBase64でエンコード
 */
function changeImageToBase64() {
    const uploadImage = $('#uploadImage')[0];
    const file = uploadImage.files[0];

    resizeImage(file, 240, 350, function (resizedDataUrl) {
        $('#base64text').val(resizedDataUrl);
        $('#uploadImageArea').html(`<img src="${resizedDataUrl}" width="100%" />`);
    });
}

function resizeImage(file, maxWidth, maxHeight, callback) {
    const reader = new FileReader();
    reader.onload = function (event) {
        const img = new Image();
        img.onload = function () {
            const canvas = document.createElement('canvas');
            let width = img.width;
            let height = img.height;

            // サイズをリサイズする
            if (width > maxWidth) {
                height *= maxWidth / width;
                width = maxWidth;
            }
            if (height > maxHeight) {
                width *= maxHeight / height;
                height = maxHeight;
            }

            canvas.width = width;
            canvas.height = height;
            const ctx = canvas.getContext('2d');
            ctx.drawImage(img, 0, 0, width, height);

            // リサイズされた画像をDataURL形式に変換してコールバック関数に渡す
            const resizedDataUrl = canvas.toDataURL('image/png'); // 画像形式はPNGに指定
            callback(resizedDataUrl);
        };
        img.src = event.target.result;
    };
    reader.readAsDataURL(file);
}


/**
 * モーダルを操作
 */
$(function () {
    $('#open_Modal').click(function (event) {
        event.preventDefault();  // デフォルト動作をキャンセル
        $('#modalArea').fadeIn();
    });
    $('#closeModal, #modalBg').click(function () {
        $('#modalArea').fadeOut();
    });
    $('#saveModalData').click(function () {
        console.log('inputPeople = ' + $('#inputPeople'))
        let serving = $('#inputPeople').val(); // servingの値を取得
        console.log('serving = ' + serving); // デバッグ用
        let ingredients = [];
        let quantities = [];

        // 材料と分量をそれぞれ取得
        $('.ingredient').each(function () {
            ingredients.push($(this).val());
        });
        $('.quantity').each(function () {
            quantities.push($(this).val());
        });

        // 材料と分量を結合して、配列に追加
        let combinedIngredients = [];
        for (let i = 0; i < ingredients.length; i++) {
            if (ingredients[i] !== '' && quantities[i] !== '') {
                combinedIngredients.push(ingredients[i] + ': ' + quantities[i]);
            }
        }

        // フォームの隠しフィールドに値を設定
        $('#hiddenServing').val(serving);
        $('#hiddenIngredients').val(combinedIngredients.join(', '));

        console.log("Hidden Serving value:", $('#hiddenServing').val());

        $('#modalArea').fadeOut();
    });
});
