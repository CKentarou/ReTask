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

// タイマーの状態を保存する関数
function saveTimerState() {
    localStorage.setItem('seconds', seconds);
    localStorage.setItem('timerRunning', timerInterval !== null);
}

// タイマーの状態を復元する関数
function restoreTimerState() {
    const savedSeconds = localStorage.getItem('seconds');
    const timerRunning = localStorage.getItem('timerRunning') === 'true';

    if (savedSeconds) {
        seconds = parseInt(savedSeconds, 10);
        const hours = Math.floor(seconds / 3600);
        const minutes = Math.floor((seconds % 3600) / 60);
        const secs = seconds % 60;

        timerDisplay.textContent = `${formatTime(hours)}:${formatTime(minutes)}:${formatTime(secs)}`;
    }

    if (timerRunning) {
        startTimer();
        startBtn.style.display = 'none';
        stopBtn.style.display = 'block';
    }
}

// タイマーを開始する関数
function startTimer() {
    timerInterval = setInterval(() => {
        seconds++;
        const hours = Math.floor(seconds / 3600);
        const minutes = Math.floor((seconds % 3600) / 60);
        const secs = seconds % 60;

        timerDisplay.textContent = `${formatTime(hours)}:${formatTime(minutes)}:${formatTime(secs)}`;
        saveTimerState();
    }, 1000);
}

// セッション開始ボタンのクリックイベント
startBtn.addEventListener('click', () => {
    const sessionId = startBtn.dataset.sessionId;

    startTimer();

    // ボタンの表示を切り替える
    startBtn.style.display = 'none';
    stopBtn.style.display = 'block';

    // Springにスタートしたことを通知
    fetch(`/api/start/${sessionId}`, { method: 'POST' });
});

// セッション停止ボタンのクリックイベント
stopBtn.addEventListener('click', () => {
    clearInterval(timerInterval);
    timerInterval = null;

    // タイマーをリセット
    seconds = 0;
    timerDisplay.textContent = '00:00:00';
    saveTimerState();
});

// ページ読み込み時にタイマーの状態を復元
document.addEventListener('DOMContentLoaded', restoreTimerState);