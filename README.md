# 🎲 RandavaProject

Randava é uma linguagem experimental e compilador voltado para aleatoriedade baseada em dados quânticos reais (Sendo a primeira no planeta até então). Seu principal foco é ser um motor de aleatoriedade que possa ser embutido em sistemas como jogos, simulações, decisões automatizadas e testes com entropia verdadeira.

Randava is an experimental language and compiler focused on randomness based on real quantum data (Being the first on the planet so far). Its main goal is to serve as a randomness engine that can be embedded in systems like games, simulations, automated decisions, and true entropy testing.

O projeto já inclui : 
- Suporte para scripts no formato .randjava
- Execução randômica baseada em dados reais de aleatoriedade quântica (API da Universidade Nacional da Austrália – ANU)
- Registro completo e detalhado de todo o processo de execução (logging)
- Mecanismo básico de segurança SHS (Simple Hash Security)
- Alternativa automática para números pseudoaleatórios caso a API esteja offline

🛡️ O Randava ainda está em estágio inicial, mas será expandido com:
- 🔐 CryptoDefense: uma camada de validação, criptografia e segurança mais robusta
- 📦 Sistema automático de empacotamento e distribuição
- 🧩 Integração com engines de jogos como Unity, Godot, Roblox e outras

The project already includes:
- Support for `.randjava` scripts
- Random execution based on Quantum Random Number Generator API (ANU)
- Detailed logging of execution process
- Basic SHS (Simple Hash Security) mechanism
- Automatic fallback to pseudo-random numbers

🛡️ Randava is in early stage and will be improved with:
- 🔐 CryptoDefense: stronger validation, security and encryption layer
- 📦 Automated packaging system
- 🧩 Integration with game engines like Unity, Godot, Roblox etc.

---

## 🇧🇷 Como usar (Português)

1. ✅ Requisitos:
   - Java JDK instalado (preferencialmente 17+)
   - Conexão com a internet para aleatoriedade quântica

2. 📁 Estrutura esperada:
```
RandavaProject/
├── src/
│   └── randava/
│       ├── LoggerRandava.java
│       ├── QuantumRandum.java
│       └── RandavaCompiler.java
├── exemplo.randjava
├── run_randava.bat
```

3. ✍️ Exemplo de arquivo .randjava:
```java
System.out.println("Início do programa");
random {
    System.out.println("Opção A");
    System.out.println("Opção B");
    System.out.println("Opção C");
}
System.out.println("Fim do programa");
```

4. ▶️ Como executar:
Abra o terminal (cmd ou powershell) na pasta principal e digite:

```bash
run_randava.bat exemplo.randjava
```

5. 📄 Resultado:
- Geração de `exemplo.java` compilado com uma escolha aleatória
- Execução automática no terminal
- Geração de log em `/logs/randava_YYYYMMDD_HHmm.log`

6. ⏱️ Tempo de resposta:
- API quântica: ~300ms a ~2000ms dependendo da rede
- Fallback pseudoaleatório se offline: instantâneo

---

## 🇺🇸 How to use (English)

1. ✅ Requirements:
   - Java JDK (17+ recommended)
   - Internet connection for quantum randomness

2. 📁 Expected folder structure:
```
RandavaProject/
├── src/
│   └── randava/
│       ├── LoggerRandava.java
│       ├── QuantumRandum.java
│       └── RandavaCompiler.java
├── example.randjava
├── run_randava.bat
```

3. ✍️ Example .randjava file:
```java
System.out.println("Start of program");
random {
    System.out.println("Option A");
    System.out.println("Option B");
    System.out.println("Option C");
}
System.out.println("End of program");
```

4. ▶️ How to run:
Open terminal (cmd or powershell) in project folder and type:

```bash
run_randava.bat example.randjava
```

5. 📄 Output:
- Generates example.java and compiles with random choice
- Runs the program immediately
- Creates log in `/logs/randava_YYYYMMDD_HHmm.log`

6. ⏱️ Response time:
- Quantum API: ~300ms to ~2000ms depending on network
- Pseudo-random fallback: instantaneous

---

🧠 Ideal para / Ideal for:
- Jogos e simulações / Games and simulations
- Geradores de loot/AI/NPC / Loot/AI/NPC generators
- Escolhas imparciais e auditáveis / Fair and auditable decision making

🔒 Segurança atual / Current security: SHS (Simples Hash Security) — basic validations. Will be expanded with CryptoDefense and digital signatures in the future.

---

📌 Licença: Código aberto. Modifique e contribua à vontade!
📌 License: Open source. Modify and contribute freely!

Autor : Toonzbad (Github) 
