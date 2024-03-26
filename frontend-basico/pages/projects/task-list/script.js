document.addEventListener("DOMContentLoaded", function () {
    // Espera que o DOM esteja totalmente carregado antes de executar o script

    // Referência aos elementos do DOM
    const taskInput = document.querySelector('input[type="text"]');
    const addTaskButton = document.getElementById('addTaskButton');

    loadTasks();

    addTaskButton.addEventListener("click", function (event) {
        event.preventDefault(); // Evita o comportamento padrão do formulário

        // Obter o valor do campo de entrada
        const content = taskInput.value;
        if (content.trim() !== '') {
            const newTask = new Task(content);
            addTask(newTask);
            saveTask(newTask);
        }
    });
});

class Task {
    constructor(content, completed = false) {
        this.content = content;
        this.completed = completed;
    }
}

function addTask(newTask) {
    const taskList = document.getElementById('taskList');
    const taskInput = document.querySelector('input[type="text"]');

    // Verificar se o campo está vazio antes de adicionar à lista
    const newTaskItem = document.createElement('li');

    // Criar checkbox para marcar/desmarcar como concluído
    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.addEventListener('change', function () {
        newTask.completed = !newTask.completed;
        saveTask(newTask); // Atualizar o localStorage quando uma tarefa é marcada/desmarcada
        updateTaskItemClass(newTaskItem, newTask.completed);
    });

    // Botão para remover a tarefa
    const removeButton = document.createElement('button');
    removeButton.textContent = 'Remover';
    removeButton.className = 'remove-button';
    removeButton.addEventListener('click', function () {
        removeTask(newTask);
    });

    // Adicionar elementos ao novo item da lista
    newTaskItem.appendChild(checkbox);
    newTaskItem.appendChild(document.createTextNode(newTask.content));
    newTaskItem.appendChild(removeButton);

    // Adicionar o elemento à lista
    taskList.appendChild(newTaskItem);

    // Atualizar a classe do item de lista com base no status da tarefa
    updateTaskItemClass(newTaskItem, newTask.completed);

    // Limpar o campo de entrada
    taskInput.value = "";

}

function saveTask(task) {
    // Carregar tarefas existentes do localStorage
    const existingTasks = JSON.parse(localStorage.getItem('tasks')) || [];

    // Encontrar a tarefa no array existente e atualizar seu estado
    const updatedTasks = existingTasks.map(existingTask => {
        if (existingTask.content === task.content) {
            return task;
        }
        return existingTask;
    });

    // Salvar o array atualizado de tarefas no localStorage
    localStorage.setItem('tasks', JSON.stringify(updatedTasks));
}

function removeTask(task) {
    const taskList = document.getElementById('taskList');
    const taskItem = Array.from(taskList.children).find(item => item.textContent.includes(task.content));

    // Remover a tarefa do DOM
    if (taskItem) {
        taskList.removeChild(taskItem);
    }

    // Carregar tarefas existentes do localStorage
    const existingTasks = JSON.parse(localStorage.getItem('tasks')) || [];

    // Remover a tarefa do array
    const updatedTasks = existingTasks.filter(existingTask => existingTask.content !== task.content);

    // Salvar o array atualizado de tarefas no localStorage
    localStorage.setItem('tasks', JSON.stringify(updatedTasks));
}

function updateTaskItemClass(taskItem, completed) {
    // Adicionar/remover a classe 'completed' com base no status da tarefa
    if (completed) {
        taskItem.classList.add('completed');
    } else {
        taskItem.classList.remove('completed');
    }
}

function loadTasks() {
    // Carregar tarefas do localStorage
    const existingTasks = JSON.parse(localStorage.getItem('tasks')) || [];

    // Adicionar as tarefas à lista
    existingTasks.forEach(function (task) {
        const newTask = new Task(task.content, task.completed)
        addTask(newTask);
    });
}