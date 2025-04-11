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

    // 必要に応じてSpringに「スタートした」ことを通知
    // fetch('/start', { method: 'POST' });
});

// セッション停止ボタンのクリックイベント
stopBtn.addEventListener('click', () => {
    clearInterval(timerInterval);

    // startBtnは再表示しない
    stopBtn.style.display = 'none';

    // Spring側に経過時間を送信
    // fetch('/stop', {
    //     method: 'POST',
    //     headers: { 'Content-Type': 'application/json' },
    //     body: JSON.stringify({ elapsed: seconds })
    // });
});