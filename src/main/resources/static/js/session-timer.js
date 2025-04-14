let seconds = 0;
let timerInterval = null;

const timerDisplay = document.getElementById('timer-display');
const startBtn = document.getElementById('start-btn');
const stopBtn = document.getElementById('stop-btn');

// 初期状態でストップボタンを非表示にする
stopBtn.style.display = 'none';

// 数値を2桁にフォーマットする関数
function formatTime(value) {
    return value.toString().padStart(2, '0');
}

// セッション開始ボタンのクリックイベント
startBtn.addEventListener('click', () => {
    //スタートボタンからセッションIDを取得
    const sessionId = document.getElementById('start-btn').dataset.sessionId;

    timerInterval = setInterval(() => {
        seconds++;

        const hours = Math.floor(seconds / 3600);
        const minutes = Math.floor((seconds % 3600) / 60);
        const secs = seconds % 60;

        timerDisplay.textContent = `${formatTime(hours)}:${formatTime(minutes)}:${formatTime(secs)}`;
    }, 1000);

    // ボタンの表示を切り替える
    startBtn.style.display = 'none';
    stopBtn.style.display = 'block';

    // Springにスタートしたことを通知
    fetch(`/api/start/${sessionId}`, {method: 'POST'})
});

// セッション停止ボタンのクリックイベント
stopBtn.addEventListener('click', () => {
    clearInterval(timerInterval);

    // startBtnは再表示しない
    stopBtn.style.display = 'none';

});