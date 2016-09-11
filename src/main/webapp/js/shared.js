angular.module('library.shared', []).factory('libraryShared',
    function () {
        var libraryShared = {};
        // patron
        libraryShared.patronHeading = {};
        libraryShared.patronHeading._010_id = {};
        libraryShared.patronHeading._010_id.name = 'id';
        libraryShared.patronHeading._010_id.text = 'Id';
        libraryShared.patronHeading._010_id.type = 'Number';
        libraryShared.patronHeading._020_name = {};
        libraryShared.patronHeading._020_name.name = 'name';
        libraryShared.patronHeading._020_name.text = 'Name';
        libraryShared.patronHeading._020_name.type = 'String';
        // media
        libraryShared.mediaHeading = {};
        libraryShared.mediaHeading._010_id = {};
        libraryShared.mediaHeading._010_id.name = "id";
        libraryShared.mediaHeading._010_id.text = "Id";
        libraryShared.mediaHeading._010_id.type = "Number";
        libraryShared.mediaHeading._020_title = {};
        libraryShared.mediaHeading._020_title.name = "title";
        libraryShared.mediaHeading._020_title.text = "Title";
        libraryShared.mediaHeading._020_title.type = "String";
        libraryShared.mediaHeading._030_author = {};
        libraryShared.mediaHeading._030_author.name = "author";
        libraryShared.mediaHeading._030_author.text = "Author";
        libraryShared.mediaHeading._030_author.type = "String";
        libraryShared.mediaHeading._040_format = {};
        libraryShared.mediaHeading._040_format.name = "format";
        libraryShared.mediaHeading._040_format.text = "Format";
        libraryShared.mediaHeading._040_format.type = "String";
        libraryShared.mediaHeading._050_genre = {};
        libraryShared.mediaHeading._050_genre.name = "genre";
        libraryShared.mediaHeading._050_genre.text = "Genre";
        libraryShared.mediaHeading._050_genre.type = "String";
        libraryShared.mediaHeading._060_isbn = {};
        libraryShared.mediaHeading._060_isbn.name = "isbn";
        libraryShared.mediaHeading._060_isbn.text = "ISBN";
        libraryShared.mediaHeading._060_isbn.type = "String";
        libraryShared.mediaHeading._070_copies = {};
        libraryShared.mediaHeading._070_copies.text = "Copies";
        libraryShared.mediaHeading._070_copies.name = "copies";
        libraryShared.mediaHeading._070_copies.type = "Number";
        // record
        libraryShared.recordHeading = {};
        libraryShared.recordHeading._010_id = {};
        libraryShared.recordHeading._010_id.name = "id";
        libraryShared.recordHeading._010_id.text = "Id";
        libraryShared.recordHeading._010_id.type = "Number";
        libraryShared.recordHeading._015_name = {};
        libraryShared.recordHeading._015_name.name = "name";
        libraryShared.recordHeading._015_name.text = "Name";
        libraryShared.recordHeading._015_name.type = "String";
        libraryShared.recordHeading._020_title = {};
        libraryShared.recordHeading._020_title.name = "title";
        libraryShared.recordHeading._020_title.text = "Title";
        libraryShared.recordHeading._020_title.type = "String";
        libraryShared.recordHeading._030_author = {};
        libraryShared.recordHeading._030_author.name = "author";
        libraryShared.recordHeading._030_author.text = "Author";
        libraryShared.recordHeading._030_author.type = "String";
        libraryShared.recordHeading._040_barcode = {};
        libraryShared.recordHeading._040_barcode.name = "barcode";
        libraryShared.recordHeading._040_barcode.text = "Barcode";
        libraryShared.recordHeading._040_barcode.type = "String";
        libraryShared.recordHeading._050_status = {};
        libraryShared.recordHeading._050_status.name = "status";
        libraryShared.recordHeading._050_status.text = "Status";
        libraryShared.recordHeading._050_status.type = "String";
        libraryShared.recordHeading._060_checkout = {};
        libraryShared.recordHeading._060_checkout.name = "checkout";
        libraryShared.recordHeading._060_checkout.text = "Checkout";
        libraryShared.recordHeading._060_checkout.type = "Date";
        libraryShared.recordHeading._070_due = {};
        libraryShared.recordHeading._070_due.name = "due";
        libraryShared.recordHeading._070_due.text = "Due date";
        libraryShared.recordHeading._070_due.type = "Date";
        libraryShared.recordHeading._080_checkin = {};
        libraryShared.recordHeading._080_checkin.name = "checkin";
        libraryShared.recordHeading._080_checkin.text = "Checkin";
        libraryShared.recordHeading._080_checkin.type = "Date";
        // return myself;
        return libraryShared;
    });