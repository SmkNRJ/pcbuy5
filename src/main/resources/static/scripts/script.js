function f(j) {
    var l = j;
        // Когда происходит изменение элементов управления, значит появились новые файлы
        var i = 0,
            files = l.files,
            len = files.length;

        for (; i < len; i++) {
            console.log("Filename: " + files[i].name);
            console.log("Type: " + files[i].type);
            console.log("Size: " + files[i].size + " bytes");
        }
}