//itemを自動で切り替える間隔(ミリ秒で指定)
let intervalTime = 3000;


//////////////////////////////////////////////////////////////////////////////////
// ↓↓ 以降のコードは触らない ↓↓ //////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////



//事前準備
let itemElements = document.getElementById('items-wrapper'); //以降の処理で使いまわすので事前に要素を取得しておく
let tabElements = document.getElementById('select-tabs');    //以降の処理で使いまわすので事前に要素を取得しておく
let itemNum = itemElements.childElementCount - 1;  //インデックスは0スタートなので、取得した要素数から1引く
let activeNum = 0;  //アクティブなitemの番号を格納
let intervalId = setInterval(changeToNextItem, intervalTime); //itemの自動切り替え



//■以降の処理で使いまわす関数

//select-tab,change-buttonが押された時に、自動切り替えのタイマーをリセットする
function resetInterval(){
    clearInterval(intervalId);
    intervalId = setInterval(changeToNextItem, intervalTime);
}

//itemの切り替えに伴うtabの切り替え処理
function changeActiveTab(fromActiveNum, toActiveNum){
    fromTab = tabElements.children[fromActiveNum];
    fromTab.classList.remove('active');

    toTab = tabElements.children[toActiveNum];
    toTab.classList.add('active');
}



// select-tabsでのitemの切り替え処理 ---------------------------
function changeToSelectItem(i){
    resetInterval();
    
    fromActiveNum = activeNum;
    toActiveNum = i;

    //アクティブなitemが選択されたら何もせず処理を終了する
    if(fromActiveNum == toActiveNum){return;}

    fromItem = itemElements.children[fromActiveNum];
    fromItem.classList.remove('active');
    fromItem.classList.remove('fadeRightIn');
    fromItem.classList.remove('fadeLeftIn');
    toItem = itemElements.children[toActiveNum];
    toItem.classList.remove('fadeLeftOut');
    toItem.classList.remove('fadeRightOut');
    if(activeNum < toActiveNum){
        //次のスライドへ移動する時
        fromItem.classList.add('fadeLeftOut')
        toItem.classList.add('fadeRightIn');
    }else{
        //前のスライドへ移動する時
        fromItem.classList.add('fadeRightOut')
        toItem.classList.add('fadeLeftIn');
    }

    changeActiveTab(fromActiveNum, toActiveNum)

    activeNum = toActiveNum;
}


// change-buttonでのitemの切り替え処理 ---------------------
function changeToPrevItem(){
    resetInterval();
    
    fromActiveNum = activeNum;

    fromItem = itemElements.children[activeNum];
    fromItem.classList.remove('active');
    fromItem.classList.remove('fadeRightIn');
    fromItem.classList.remove('fadeLeftIn');

    if(activeNum == 0){
        activeNum = itemNum;
    }else{
        --activeNum;
    }

    toActiveNum = activeNum;

    toItem = itemElements.children[activeNum];
    toItem.classList.remove('fadeLeftOut');
    toItem.classList.remove('fadeRightOut');

    fromItem.classList.add('fadeRightOut')
    toItem.classList.add('fadeLeftIn');
    toItem.classList.add('active');

    changeActiveTab(fromActiveNum, toActiveNum)
}
function changeToNextItem(){
    resetInterval();
    
    fromActiveNum = activeNum;

    fromItem = itemElements.children[activeNum];
    fromItem.classList.remove('active');
    fromItem.classList.remove('fadeRightIn');
    fromItem.classList.remove('fadeLeftIn');

    if(activeNum == itemNum){
        activeNum = 0;
    }else{
        ++activeNum;
    }
    
    toActiveNum = activeNum;

    toItem = itemElements.children[activeNum];
    toItem.classList.remove('fadeLeftOut');
    toItem.classList.remove('fadeRightOut');

    fromItem.classList.add('fadeLeftOut')
    toItem.classList.add('fadeRightIn');
    toItem.classList.add('active');

    changeActiveTab(fromActiveNum, toActiveNum)
}


// //itemを自動で切り替える間隔(ミリ秒で指定)
// let intervalTime = 3000;

// //////////////////////////////////////////////////////////////////////////////////
// // ↓↓ 以降のコードは触らない ↓↓ //////////////////////////////////////////////////////////
// //////////////////////////////////////////////////////////////////////////////////

// //事前準備
// let itemElements = document.getElementById('items-wrapper'); //以降の処理で使いまわすので事前に要素を取得しておく
// let tabElements = document.getElementById('select-tabs');    //以降の処理で使いまわすので事前に要素を取得しておく
// let itemNum = itemElements.childElementCount - 1;  //インデックスは0スタートなので、取得した要素数から1引く
// let activeNum = 0;  //アクティブなitemの番号を格納
// let intervalId = setInterval(changeToNextItem, intervalTime); //itemの自動切り替え

// //■以降の処理で使いまわす関数

// //select-tab,change-buttonが押された時に、自動切り替えのタイマーをリセットする
// function resetInterval(){
//     clearInterval(intervalId);
//     intervalId = setInterval(changeToNextItem, intervalTime);
// }

// //itemの切り替えに伴うtabの切り替え処理
// function changeActiveTab(fromActiveNum, toActiveNum){
//     let fromTab = tabElements.children[fromActiveNum];
//     fromTab.classList.remove('active');

//     let toTab = tabElements.children[toActiveNum];
//     toTab.classList.add('active');
// }

// // select-tabsでのitemの切り替え処理 ---------------------------
// function changeToSelectItem(i){
//     resetInterval();
    
//     let fromActiveNum = activeNum;
//     let toActiveNum = i;

//     //アクティブなitemが選択されたら何もせず処理を終了する
//     if(fromActiveNum == toActiveNum){return;}

//     let fromItem = itemElements.children[fromActiveNum];
//     fromItem.classList.remove('active');
//     fromItem.classList.remove('fadeRightIn');
//     fromItem.classList.remove('fadeLeftIn');
//     let toItem = itemElements.children[toActiveNum];
//     toItem.classList.remove('fadeLeftOut');
//     toItem.classList.remove('fadeRightOut');
//     if(activeNum < toActiveNum){
//         //次のスライドへ移動する時
//         fromItem.classList.add('fadeLeftOut');
//         toItem.classList.add('fadeRightIn');
//     }else{
//         //前のスライドへ移動する時
//         fromItem.classList.add('fadeRightOut');
//         toItem.classList.add('fadeLeftIn');
//     }

//     changeActiveTab(fromActiveNum, toActiveNum);

//     activeNum = toActiveNum;
//     toItem.classList.add('active');
// }

// // change-buttonでのitemの切り替え処理 ---------------------
// function changeToPrevItem(){
//     resetInterval();
    
//     let fromActiveNum = activeNum;

//     let fromItem = itemElements.children[activeNum];
//     fromItem.classList.remove('active');
//     fromItem.classList.remove('fadeRightIn');
//     fromItem.classList.remove('fadeLeftIn');

//     if(activeNum == 0){
//         activeNum = itemNum;
//     }else{
//         --activeNum;
//     }

//     let toActiveNum = activeNum;

//     let toItem = itemElements.children[activeNum];
//     toItem.classList.remove('fadeLeftOut');
//     toItem.classList.remove('fadeRightOut');

//     fromItem.classList.add('fadeRightOut');
//     toItem.classList.add('fadeLeftIn');
//     toItem.classList.add('active');

//     changeActiveTab(fromActiveNum, toActiveNum);
// }

// function changeToNextItem(){
//     resetInterval();
    
//     let fromActiveNum = activeNum;

//     let fromItem = itemElements.children[activeNum];
//     fromItem.classList.remove('active');
//     fromItem.classList.remove('fadeRightIn');
//     fromItem.classList.remove('fadeLeftIn');

//     if(activeNum == itemNum){
//         activeNum = 0;
//     }else{
//         ++activeNum;
//     }
    
//     let toActiveNum = activeNum;

//     let toItem = itemElements.children[activeNum];
//     toItem.classList.remove('fadeLeftOut');
//     toItem.classList.remove('fadeRightOut');

//     fromItem.classList.add('fadeLeftOut');
//     toItem.classList.add('fadeRightIn');
//     toItem.classList.add('active');

//     changeActiveTab(fromActiveNum, toActiveNum);
// }
