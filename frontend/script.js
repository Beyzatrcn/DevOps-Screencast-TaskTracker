document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("taskForm");
    const nameInput = document.getElementById("name");
    const statusInput = document.getElementById("status");
    const taskList = document.getElementById("taskList");
  
    function loadTasks() {
      fetch("http://localhost:8080/tasks")
        .then(response => response.json())
        .then(data => {
          taskList.innerHTML = ""; // Liste leeren
          data.forEach(task => {
            const li = document.createElement("li");
            li.textContent = `${task.title} - ${task.status}`;
            taskList.appendChild(li);
          });
        });
    }
  
    form.addEventListener("submit", function (e) {
      e.preventDefault();
  
      const task = {
        title: nameInput.value,
        status: statusInput.value
      };
  
      fetch("http://localhost:8080/tasks", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(task)
      })
        .then(response => {
          if (!response.ok) {
            throw new Error("Fehler beim Hinzufügen der Aufgabe.");
          }
          return response.json();
        })
        .then(() => {
          loadTasks(); // Aufgaben neu laden
          form.reset(); // Eingabefelder zurücksetzen
        })
        .catch(error => {
          console.error("Fehler:", error);
        });
    });
  
    loadTasks(); // Initial laden
  });