const ctx = document.getElementById('myPieChart').getContext('2d');
new Chart(ctx, {
    type: 'pie', // グラフの種類
    data: {
        labels: ['完了', '未完了'], // ラベル
        datasets: [{
            data: chartData, // Thymeleafから渡されたデータを使用
            backgroundColor: ['#3cb371', '#dcdcdc'], // 色
        }]
    },
    options: {
        responsive: true, // レスポンシブ対応
    }
});