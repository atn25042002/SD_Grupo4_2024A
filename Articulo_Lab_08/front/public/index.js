let registers = [];
let currentSection;
let addEntityModal; // modal bootstrap

document.addEventListener("DOMContentLoaded", function() {
    // Selecciona los botones de navegación
    const btnDepartamentos = document.getElementById("btn-departamentos");
    const btnProyectos = document.getElementById("btn-proyectos");
    const btnIngenieros = document.getElementById("btn-ingenieros");
    const btnIngenierosProyecto = document.getElementById("btn-buscarIngenieros");
                        
    // Selecciona las secciones de contenido
    const sectionDepartamentos = document.getElementById("departamentos-section");
    const sectionProyectos = document.getElementById("proyectos-section");
    const sectionIngenieros = document.getElementById("ingenieros-section");

    // Selecciona el modal y el botón de guardar
    addEntityModal = new bootstrap.Modal(document.getElementById("addEntityModal"));
    const saveEntityBtn = document.getElementById("save-entity-btn");
    const modalContent = document.getElementById("modal-content");

    // Carga la data inicial para los departamentos 
    loadSection(sectionDepartamentos, "departamentos");

    function changeSection(activeSection) {
        sectionDepartamentos.classList.remove("active");
        sectionProyectos.classList.remove("active");
        sectionIngenieros.classList.remove("active");

        btnDepartamentos.classList.remove("active");
        btnProyectos.classList.remove("active");
        btnIngenieros.classList.remove("active");

        activeSection.section.classList.add("active");
        activeSection.button.classList.add("active");

        loadSection(activeSection.section, activeSection.endPoint);
    }

    // Añadir eventos a los botones de navegación
    btnDepartamentos.addEventListener("click", function() {
        changeSection({ section: sectionDepartamentos, button: btnDepartamentos, endPoint: "departamentos" });
    });

    btnProyectos.addEventListener("click", function() {
        changeSection({ section: sectionProyectos, button: btnProyectos, endPoint: "proyectos" });
    });

    btnIngenieros.addEventListener("click", function() {
        changeSection({ section: sectionIngenieros, button: btnIngenieros, endPoint: "ingenieros" });
    });

    btnIngenierosProyecto.addEventListener("click", function() {
        let idProyecto = document.getElementById('input-searchIngenieros').value;
        loadIngenierosByProject(idProyecto, "ingenieros");
    });

    // Manejar el evento de clic en los botones "Añadir"
    document.getElementById("add-department-btn").addEventListener("click", function() {
        openModalForEntity("departamentos");
    });

    document.getElementById("add-project-btn").addEventListener("click", function() {
        openModalForEntity("proyectos");
    });

    document.getElementById("add-engineer-btn").addEventListener("click", function() {
        openModalForEntity("ingenieros");
    });

    // Función para abrir el modal y configurar el contenido dinámico
    function openModalForEntity(entityType) {
        changeModalForEntity(entityType, modalContent);
        addEntityModal.show();
        // Guardar el tipo de entidad actual en el botón de guardar
        saveEntityBtn.dataset.entityType = entityType;
        saveEntityBtn.dataset.edit = false;
    }

    // Manejar el evento de clic en el botón de guardar del modal
    saveEntityBtn.addEventListener("click", function() {
        const entityType = saveEntityBtn.dataset.entityType;
        
        // update options
        const edit = saveEntityBtn.dataset.edit;
        const id = saveEntityBtn.dataset.id;

        // data que sera enviada al back
        let entityData = {};

        switch (entityType) {
            case "departamentos":
                entityData = {
                    nombre: document.getElementById("entity-name").value,
                    telefono: document.getElementById("entity-phone").value,
                    fax: document.getElementById("entity-fax").value
                };
                break;
            case "proyectos":
                entityData = {
                    nombre: document.getElementById("entity-name").value,
                    fecInicio: document.getElementById("entity-fechaInit").value,
                    fecTermino: document.getElementById("entity-fechaFin").value,
                    idDpto: document.getElementById("entity-departments").value,
                    //idIng: document.getElementById("entity-ingenieros").value
                };
                break;
            case "ingenieros":
                entityData = {
                    nombre: document.getElementById("entity-name").value,
                    especialidad: document.getElementById("entity-specialty").value,
                    cargo: document.getElementById("entity-cargo").value,
                    idproy: document.getElementById("entity-proyecto").value
                };
                break;
        }

        if (edit === "true") {
            updateEntity(entityData, id, entityType, modalContent);
        } else {
            addEntity(entityData, entityType, modalContent);
        }
    });
});

// Carga inicial
function loadSection(seccion, endPoint) {
    fetch(`http://localhost:8080/gestionEmpresa/${endPoint}`, {
        mode: 'cors',
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        }
        throw new error("error en la solicitud al servidor");
    })
    .then(data => {
        let tbody = seccion.getElementsByTagName("tbody")[0];
        currentSection = tbody;
        // modifica la lista de entidades
        registers = [];
        data.forEach(element => {
            registers[element.id] = element;
        });
        console.log(registers);
        reloadSection(endPoint);
    })
    .catch(error => {
        console.log(error);
        alert("error al cargar los departamentos");
    })

}

// Recarga con register
function reloadSection(endPoint) {
    currentSection.innerHTML = "";

    // modifica la lista de entidades
    registers.forEach(element => {
        let child = "<tr>";
        for (let key in element) {
            child += `<td>${element[key]}</td>`;
        }
        // agregando botones editar y borrar
        child += `<td> <button class='btn btn-warning btn-sm' onclick='onClickButtonUpdateEntity(${element.id}, "${endPoint}")'>Editar</button> <button class='btn btn-danger btn-sm' onclick='deleteEntity(${element.id}, "${endPoint}")'>Borrar</button></td>`;
        child += "</tr>";

        currentSection.innerHTML += child;
    });
}

function addEntity(entityData, entityType, modalContent) {
    // Enviar los datos al backend
    fetch(`http://localhost:8080/gestionEmpresa/${entityType}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entityData)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        }
        throw new Error('Error en la solicitud al servidor');
    })
    .then(data => {
        alert(`${entityType} añadido con éxito: ${data.nombre}`);

        registers[data.id] = data;

        // Cierra y limpia el contenido del modal
        addEntityModal.hide();
        modalContent.innerHTML = '';

        reloadSection(entityType);
    })
    .catch(error => {
        console.log(error);
        alert('Hubo un error al añadir la entidad. Por favor, inténtelo de nuevo.');
    });
}

function deleteEntity(id, section) {
    fetch(`http://localhost:8080/gestionEmpresa/${section}/${id}`, {
        mode: 'cors',
        method: 'DELETE',
        headers: {
            'content-type': 'application/json',
        },
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        }
        return response.json().then(errorInfo => {
            throw new Error(errorInfo.message);
        });
    })
    .then(data => {
        delete registers[id];
        reloadSection(section);
        alert(`${data.nombre} eliminado con exito`);
    })
    .catch(error => {
        alert(error);
    })
}

function updateEntity(entityData, id, entityType, modalContent) {
    fetch(`http://localhost:8080/gestionEmpresa/${entityType}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entityData)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        }
        throw new Error('Error en la solicitud al servidor');
    })
    .then(data => {
        alert(`${entityType} actualizado con éxito: ${data.nombre}`);

        registers[data.id] = data;

        // Cierra y limpia el contenido del modal
        addEntityModal.hide();
        modalContent.innerHTML = '';

        reloadSection(entityType);
    })
    .catch(error => {
        console.log(error);
        alert('Hubo un error al actualizar la entidad. Por favor, inténtelo de nuevo.');
    });
}

// Cambia el contenido del modal basado en el tipo de entidad
function changeModalForEntity(entityType, modalContent, edit = false, id = -1) {
    let entity = edit? registers[id] : null;

    switch (entityType) {
        case "departamentos":
            modalContent.innerHTML = `
                <div class="form-group">
                    <label for="entity-name">Nombre del Departamento</label>
                    <input type="text" class="form-control" id="entity-name" ${edit ? "value='"+ entity.nombre+ "'": ""} required>
                </div>
                <div class="form-group">
                    <label for="entity-phone">Teléfono</label>
                    <input type="text" class="form-control" id="entity-phone" ${edit ? "value='"+ entity.telefono + "'": ""}  required>
                </div>
                <div class="form-group">
                    <label for="entity-fax">Fax</label>
                    <input type="text" class="form-control" id="entity-fax" ${edit ? "value='"+ entity.fax + "'": ""}required>
                </div>
            `;
            break;
        case "proyectos":
            modalContent.innerHTML = `
                <div class="form-group">
                    <label for="entity-name">Nombre del Proyecto</label>
                    <input type="text" class="form-control" id="entity-name" ${edit ? "value='"+ entity.nombre+ "'": ""} required>
                </div>
                <div class="form-group">
                    <label for="entity-fechaInit">Fecha de inicio</label>
                    <input type="date" class="form-control" id="entity-fechaInit" ${edit ? "value='"+ entity.fecInicio+ "'": ""} required>
                </div>
                <div class="form-group">
                    <label for="entity-fechaFin">Duración</label>
                    <input type="date" class="form-control" id="entity-fechaFin" ${edit ? "value='"+ entity.fecTermino+ "'": ""} required>
                </div>
                <div class="form-group">
                    <label for="entity-departments">Departamentos IDs</label>
                    <input type="text" class="form-control" id="entity-departments" ${edit ? "value='"+ entity.idDpto+ "'": ""} required>
                </div>
            `;
            break;
        case "ingenieros":
            modalContent.innerHTML = `
                <div class="form-group">
                    <label for="entity-name">Nombre del Ingeniero</label>
                    <input type="text" class="form-control" id="entity-name" ${edit ? "value='"+ entity.nombre+ "'": ""} required>
                </div>
                <div class="form-group">
                    <label for="entity-specialty">Especialidad</label>
                    <input type="text" class="form-control" id="entity-specialty" ${edit ? "value='"+ entity.especialidad+ "'": ""} required>
                </div>
                <div class="form-group">
                    <label for="entity-cargo">Cargo</label>
                    <input type="text" class="form-control" id="entity-cargo" ${edit ? "value='"+ entity.cargo+ "'": ""} required>
                </div>
                <div class="form-group">
                    <label for="entity-proyecto">ID Proyecto</label>
                    <input type="text" class="form-control" id="entity-proyecto" ${edit ? "value='"+ entity.idproy+ "'": ""} required>
                </div>
            `;
            break;
    }

    addEntityModal.show();
}

function onClickButtonUpdateEntity(element, endPoint) {
    changeModalForEntity(endPoint, document.getElementById("modal-content"), true, element);
    const saveEntityBtn = document.getElementById("save-entity-btn");
    saveEntityBtn.dataset.edit = true;
    saveEntityBtn.dataset.entityType = endPoint;
    saveEntityBtn.dataset.id = element;
}

function loadIngenierosByProject(id, endPoint) {
    fetch(`http://localhost:8080/gestionEmpresa/${endPoint}/proyecto/${id}`, {
        mode: 'cors',
        method: 'GET',
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        }
        throw new error("error en la solicitud al servidor");
    })
    .then(data => {
        registers = [];
        data.forEach(element => {
            registers[element.id] = element;
        });
        reloadSection(endPoint);
    })
    .catch(error => {
        console.log(error);
        alert("error al cargar los ingenieros del proyecto");
    })
}