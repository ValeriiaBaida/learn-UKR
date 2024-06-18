const lessons = document.querySelectorAll('.lesson')
const taskBars = document.querySelectorAll('.tasks-progress-bar')

lessons.forEach(lesson => {
    try{
        const progressScroll = lesson.querySelector('.lesson-progress-value')
        const progressTitle = lesson.querySelector('.progress-title')
        const progressValue = lesson.querySelector('.progress-value')
        const progress = parseInt(progressValue.textContent)
    
        if (progress === 100) {
            progressTitle.textContent = "Пройдено"
            progressTitle.classList.add('done')
            progressScroll.style.backgroundColor = '#2A80F4'
            progressValue.style.display = 'none'
        }
        if (progress === 0) {
            progressTitle.textContent = "Не розпочато"
            progressValue.style.display = 'none'
        }
        progressScroll.style.width = `${progress}%`
    }catch(error){

    }
})

taskBars.forEach(bar => {
    const value = parseFloat(bar.querySelector('.tasks-progress-value').textContent)
    if (bar.classList.contains('answered-progress-task')) {
        bar.style.background = `conic-gradient(#f2ac4a ${value * 3.6}deg, #f2f2f2 0deg)`
    } else if (bar.classList.contains('completed-progress-task')) {
        bar.style.background = `conic-gradient(#2a80f4 ${value * 3.6}deg, #f2f2f2 0deg)`
    }
})