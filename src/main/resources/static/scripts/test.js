const tasks = document.querySelectorAll('.task')


tasks.forEach(task => {
    const check = task.querySelector('.check')
    const answers = task.querySelectorAll('.answer')

    answers.forEach((answer) => {
        answer.addEventListener('change', () => {
            check.disabled = false
        })
    })


    check.onclick = () => {
        if (check.disabled) {
            return
        } else {
            check.style.display = 'none'
        }
        const answer = task.querySelector('.answer:has(input:checked)');
        const data = {
            id: task.id,
            answer: task.querySelector('input:checked').value
        }

        fetch('', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(responseData => {
                if (responseData.result) {
                    answer.classList.add('correct')
                } else {
                    answer.classList.add('incorrect')
                    answers.forEach(answer => {
                        if (answer.textContent.trim() === responseData.correctAnswer) {
                            answer.classList.add('correct')
                        }
                    })
                }
                answers.forEach(answer => {
                    answer.querySelector('input').disabled = true
                })
            })
    }
})