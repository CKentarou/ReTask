// フォームを表示する関数
function showTaskForm() {
    document.getElementById('task-name').value = ''; // フォームを空にする
    document.getElementById('modal-background').style.display = 'block';
    updateButtonState(); // ボタンの状態を初期化
}

// フォームを非表示にする関数
function hideTaskForm() {
    console.log("閉じる");
    document.getElementById('modal-background').style.display = 'none';
}

// フォームの入力内容をチェックしてボタンの状態を更新する関数
function updateButtonState() {
    const taskName = document.getElementById('task-name').value.trim();
    const actionButton = document.getElementById('action-button');

    if (taskName === '') {
        actionButton.textContent = '閉じる';
        actionButton.onclick = hideTaskForm;
    } else {
        actionButton.textContent = '追加';
        actionButton.onclick = handleTaskForm;
    }
}

// タスクを作成する関数
function createTask() {
    const taskName = document.getElementById('task-name').value.trim();
    const sessionId = document.getElementById('add-task-btn').dataset.sessionId;
    console.log("タスク名:", taskName, "セッションID:", sessionId);

    fetch(`/api/task/${sessionId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name: taskName })
    })
}

// フォームの入力内容をチェックして処理を分岐する関数
function handleTaskForm() {
    const taskName = document.getElementById('task-name').value.trim();

    if (taskName !== '') {
        createTask();
        document.getElementById('task-name').value = ''; // フォームをクリア
        hideTaskForm();
    }
}

// 入力フィールドのイベントリスナーを設定
document.getElementById('task-name').addEventListener('input', updateButtonState);