# MKV Editor

Java GUI for editing MKV media files

dependencies:
- VLC
- mkvtoolnix

### To setup the IDE:

- go to run configurations > add a new configuration
- select `Maven` from the dropdown list
- in the `Command line` field, enter `javafx:run`
- make sure `Build` is listed in the `Before Launch` section

### To run from the command line:

```
mvn javafx:run
```

### Resources:

- [Official website for the MKV Standard](https://matroska.org/index.html)
- [Tips on javafx layouts](https://edencoding.com/javafx-layouts/)





Notes:
MKV standard uses ISO 639-2 AND BCP 47 language codes.

## Licence (MIT):
Copyright 2022, Scott Harrison 

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.