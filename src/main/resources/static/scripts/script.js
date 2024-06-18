
const currentUrl = window.location.href;
const isTasks = currentUrl.split('/').slice(-1)[0] === 'tasks'

if (isTasks) {
    const taskTitle = document.querySelector('.task-section')
    taskTitle.classList.add('current')
} else {
    const lessonNumber = +((currentUrl.match('l=(\\d+)') || [])[1] || '1')
    const sectionNumber = +((currentUrl.match('s=(\\d+)') || [])[1] || '1')
    const lessons = document.querySelector('.lessons')
    const lesson = lessons.querySelector(`.lesson:nth-child(${lessonNumber})`)
    const lessonTitle = lesson.querySelector('.lesson-title')
    const lessonSection = lesson.querySelector(`.lesson-fragment:nth-child(${sectionNumber})`)

    lessonTitle.classList.add("current")
    lessonSection.classList.add("current")
}
