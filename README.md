# Pseudo-Assembler Integrated Development Environment
A modern, simple and powerful IDE for Pseudo-Assembler. 
Allows to write Pseudo-Assembler programs like never before due to the syntax highlighting and autocompletion.
It is highly customizable and intuitive.

## Run the project

To run the application do the following steps:

- Make sure to be in the root directory of the project
- Run the project using the command:

```
gradlew run
```

## Building the project

You have several options when it comes to building the project:

### Jar executable

To build jar executable run the following command:

```
gradlew jar
```

Your jar file will be located in `${PROJECT_ROOT}/build/libs`.  
You can run it using the following command:

```
java -jar ${JAR_FILE}
```

### Zipped runtime

It includes scripts to run the application, as well as bundled minimalistic JRE.  
To create zipped runtime run:

```
gradlew runtimeZip
```

After successful operation zipped runtime will be located in `${PROJECT_ROOT}/build/image.zip`.  
Extract archive somewhere, then in `${SOMEWHERE}/image/bin` use the command to run the application:

```
pseudo-assembler-ide
```

### Installer

To build the installer you will need:

- Java JDK 14+
- WiX Toolset (to build .msi installer)

After installing prerequisites simply run:

```
gradlew jpackage -PinstallerType=msi
```

You can build also different kind of installer depending on your needs, such as:

- deb
- pkg
- rpm

You can find installer afterwards in `${PROJECT_ROOT}/image/jpackage`.

# Dependencies
- RSyntaxTextArea version >= 3.0.8
- FlatLaf version >= 0.24
- Pseudo-Assembler Emulator version >= 3.1.6
# Gallery
![PAIDE_01](https://raw.githubusercontent.com/tomasz-herman/PseudoAssemblerIDE/master/gallery/PAIDE_01.png)
![PAIDE_02](https://raw.githubusercontent.com/tomasz-herman/PseudoAssemblerIDE/master/gallery/PAIDE_02.png)
![PAIDE_03](https://raw.githubusercontent.com/tomasz-herman/PseudoAssemblerIDE/master/gallery/PAIDE_03.png)
![PAIDE_04](https://raw.githubusercontent.com/tomasz-herman/PseudoAssemblerIDE/master/gallery/PAIDE_04.png)
![PAIDE_05](https://raw.githubusercontent.com/tomasz-herman/PseudoAssemblerIDE/master/gallery/PAIDE_05.png)