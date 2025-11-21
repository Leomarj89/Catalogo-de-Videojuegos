CREATE TABLE juego (
    juegoId INT IDENTITY(1,1) PRIMARY KEY,
    titulo NVARCHAR(255) NOT NULL,
    anioLanzamiento INT,
    desarrollador NVARCHAR(255),
    editora NVARCHAR(255),
    genero NVARCHAR(100),
    plataforma NVARCHAR(100),
    descripcion NVARCHAR(MAX),
    portada NVARCHAR(500)
);

CREATE TABLE usuario (
    usuarioId INT IDENTITY(1,1) PRIMARY KEY,
    juegoId INT NOT NULL,
    fechaInicio DATE,
    fechaCompletado DATE,
    horasJugadas INT,
    puntaje DECIMAL(3,1), -- como máxima 10
    estadoTermino NVARCHAR(50), -- Ejemplo: "Completado", "En progreso", "Abandonado".
    notas NVARCHAR(MAX),
    FOREIGN KEY (juegoId) REFERENCES juego(juegoId)
);

CREATE TABLE etiqueta (
    etiquetaId INT IDENTITY(1,1) PRIMARY KEY,
    nombreEtiqueta NVARCHAR(100) UNIQUE
);

CREATE TABLE usuario_etiqueta (
    usuarioId INT NOT NULL,
    etiquetaId INT NOT NULL,
    PRIMARY KEY (usuarioId, etiquetaId),
    FOREIGN KEY (usuarioId) REFERENCES usuario(usuarioId),
    FOREIGN KEY (etiquetaId) REFERENCES etiqueta(etiquetaId)
);