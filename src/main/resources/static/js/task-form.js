// フォームを表示する関数
function showTaskForm() {
    document.getElementById('task-name').value = ''; // フォームを空にする
    document.getElementById('modal-background').style.display = 'block';
    updateButtonState(); // ボタンの状態を初期化
}

// フォームを非表示にする関数
function hideTaskForm() {
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

    fetch(`/api/task/${sessionId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name: taskName })
    }).then(() => {
        loadTasks(sessionId);
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

// タスクを取得してテーブルに表示する関数
function loadTasks(sessionId) {
    fetch(`/api/task/${sessionId}`)
        .then(response => response.json())
        .then(tasks => {
            const table = document.querySelector('.table tbody');
            table.innerHTML = ''; // テーブルをクリア

            tasks.forEach(task => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td class="text-start fs-5">
                        <input type="checkbox" id="Task${task.taskId}" name="Task${task.taskId}" ${task.status === 'COMPLETED' ? 'checked' : ''}/>
                        <label for="Task${task.id}">${task.taskName}</label>
                    </td>
                `;
                table.appendChild(row);
            });
        });
}

document.addEventListener('DOMContentLoaded', () => {
    const sessionId = document.getElementById('add-task-btn').dataset.sessionId;
    loadTasks(sessionId);
});

// チェックボックスの状態変更を監視してサーバーにリクエストを送信する関数
function updateTaskStatus(taskId, isCompleted) {
    const status = isCompleted ? 'COMPLETED' : 'PENDING';

    fetch(`/api/task/${taskId}/status`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ status: status })
    }).then(response => {
        if (!response.ok) {
            console.error('Failed to update task status');
        }
    });
}

// チェックボックスにイベントリスナーを追加
document.addEventListener('change', (event) => {
    if (event.target.type === 'checkbox' && event.target.id.startsWith('Task')) {
        const taskId = event.target.id.replace('Task', '');
        const isCompleted = event.target.checked;
        updateTaskStatus(taskId, isCompleted);
    }
});