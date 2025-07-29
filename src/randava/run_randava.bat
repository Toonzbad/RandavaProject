@echo off
setlocal

:: Define diretórios e arquivos
set PROJ_DIR=%~dp0
set LOG_DIR=%PROJ_DIR%logs
set INPUT_FILE=%1

if "%INPUT_FILE%"=="" (
    echo Uso: run_randava.bat arquivo.randjava
    goto :eof
)

:: Cria pasta logs se não existir
if not exist "%LOG_DIR%" (
    mkdir "%LOG_DIR%"
)

:: Define nome do log com timestamp
for /f "tokens=1-4 delims=/: " %%a in ("%date% %time%") do (
    set LOGFILE=randava_%%a%%b%%c_%%d.log
)

set LOGPATH=%LOG_DIR%\%LOGFILE%

echo Compilando QuantumRandum.java e RandavaCompiler.java... > "%LOGPATH%"
javac QuantumRandum.java RandavaCompiler.java >> "%LOGPATH%" 2>&1

echo Compilando o arquivo Randava %INPUT_FILE%... >> "%LOGPATH%"
java RandavaCompiler %INPUT_FILE% >> "%LOGPATH%" 2>&1

echo Compilando o programa gerado ProgramaGerado.java... >> "%LOGPATH%"
javac ProgramaGerado.java >> "%LOGPATH%" 2>&1

echo Executando o programa gerado... >> "%LOGPATH%"
java ProgramaGerado >> "%LOGPATH%" 2>&1

echo. >> "%LOGPATH%"
echo Execução finalizada. Log salvo em: %LOGPATH%
echo Pressione qualquer tecla para continuar...
pause > nul