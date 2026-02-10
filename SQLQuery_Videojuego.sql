CREATE DATABASE Videojuegos;
GO

USE Videojuegos;
GO

---------------------------------------------------
-- 2. TABLA JUEGO
---------------------------------------------------
CREATE TABLE dbo.juego (
    juegoId        INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    titulo         NVARCHAR(255) NOT NULL,
    anioLanzamiento INT NULL,
    desarrollador  NVARCHAR(255) NULL,
    editora        NVARCHAR(255) NULL,
    genero         NVARCHAR(100) NULL,
    plataforma     NVARCHAR(50) NULL,
    descripcion    NVARCHAR(MAX) NULL,
    portada        NVARCHAR(500) NULL,
    horasJugadas   INT NULL,
    puntaje        DECIMAL(3,1) NULL,
    estadoTermino  NVARCHAR(50) NULL,
    notas          NVARCHAR(MAX) NULL
);
GO

---------------------------------------------------
-- 3. TABLA ETIQUETA
---------------------------------------------------
CREATE TABLE dbo.etiqueta (
    etiquetaId     INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    nombreEtiqueta NVARCHAR(100) NOT NULL UNIQUE
);
GO

---------------------------------------------------
-- 4. TABLA INTERMEDIA JUEGO_ETIQUETA
---------------------------------------------------
CREATE TABLE dbo.juego_etiqueta (
    juegoId    INT NOT NULL,
    etiquetaId INT NOT NULL,
    CONSTRAINT PK_juego_etiqueta PRIMARY KEY (juegoId, etiquetaId),
    CONSTRAINT FK_juego_etiqueta_juego
        FOREIGN KEY (juegoId) REFERENCES dbo.juego (juegoId),
    CONSTRAINT FK_juego_etiqueta_etiqueta
        FOREIGN KEY (etiquetaId) REFERENCES dbo.etiqueta (etiquetaId)
);
GO

---------------------------------------------------
-- 5. (Opcional) Semilla de etiquetas base
---------------------------------------------------
INSERT INTO dbo.etiqueta (nombreEtiqueta) VALUES
    (N'Soulslike'),
    (N'Indie'),
    (N'Favorito'),
    (N'Coop'),
    (N'Retro');
GO