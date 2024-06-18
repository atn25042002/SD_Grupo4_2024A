const express = require('express');
const app = express();
const port = 8000;

// Servir archivos estáticos desde la carpeta 'public'
app.use(express.static('public'));

app.listen(port, () => {
    console.log(`Servidor estático escuchando en http://localhost:${port}`);
});