
[![](https://jitpack.io/v/dionisiofilho/CustomDialog.svg)](https://jitpack.io/#dionisiofilho/CustomDialog)

# Custom Dialog

* Adicione isto em seu arquivo build.gradle (Project: name_project):

```sh
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
* Adicione ao build.gradle (Module: app):

 ```sh
dependencies {
        implementation 'com.github.dionisiofilho:CustomDialog:1.0.0'
}
```

# Utilizando o CustomDialog
```sh
 CustomDialog.Builder(this)
                .setTitle("Título")
                .setMessage("Mensagem")
                .setDialogType(CustomDialog.CustomDialogType.ERROR_ALERT)
                .listenerConfirm { }
                .listenerCancel { }
                .build()
 ```  


# Funções:
  - setTitle -> Adiciona o título para o dialogo.
  - setMessage -> Adiciona a mensagem para o dialogo
  - setDialogType -> informa qual o tipo do dialogo existem 3 : Confirmação, Informação e Erro.
  - listenerConfirm -> Executa uma ação se a interação for positiva.
  - listenerCancel -> Executa uma ação se a interação for negada. 
  - showDialod -> mostra o dialog na tela.



License
----


