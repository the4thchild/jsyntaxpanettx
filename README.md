# JSyntaxPaneTTx

JSyntaxPaneTTx is a fork of [JSyntaxPane](https://code.google.com/archive/p/jsyntaxpane/), a syntax highlighting library built in Java.

Additional features in this fork include:

* Support extended from Java 6 through 11
* Customizable font size
* Additional language support (just the basics so far): Perl, Nasal
* Initially forked for use in the [Text Trix](https://github.com/the4thchild/texttrix) editor

The JSyntaxPaneTTx library can be used independently in other Java-based text editors for syntax highlighting.

## Compile

### Dependencies

* JDK 6+ (mostly tested on 8-11)
* Maven

### Build

```
# output a .jar file to target folder
git clone https://github.com/the4thchild/jsyntaxpanettx.git
cd jsyntaxpanettx
mvn package

# alternatively, build from within texttrix for use there; 
# assume texttrix has been cloned already to a directory 
# alongside jsyntaxpanettx
texttrix/build.sh --jsyn
```

## Add Language Support

Support for additional languages can be added by creating new lexers and syntax kits for them. The simplest way to add this support is to copy an existing related language with a few updates:

* Copy a JFlex file from `src/main/jflex/jsyntaxpane/lexers` and name it with the language you're adding (eg copy `c.flex` to `nas.flex`)
* Update any reference to the language name in the `.flex` file
* Similarly, copy and update the corresponding Java file in 
 `src/main/java/jsyntaxpane/syntaxkits`
* In `src/main/resources/META-INF/services/jsyntaxpane`:
  * Add a line in `kitsfortypes.properties` to include the new language lexer
  * Copy and update the corresponding `syntaxkits/[lang]syntaxkit` folder
* Rebuild JSyntaxPaneTTx
* Load your new syntax kit by specifying it with `setContentType("text/[ext]");`, where `ext` matches the extension name you gave in `kitsfortypes.properties`
