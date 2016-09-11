package com.ohmyapp.library.transaction.dao;

import com.google.gson.Gson;
import com.ohmyapp.library.pojo.MediaCopyPojo;
import com.ohmyapp.library.pojo.MediaPojo;
import com.ohmyapp.library.pojo.PatronPojo;
import com.ohmyapp.library.pojo.RecordPojo;
import com.ohmyapp.library.service.LibraryException;
import com.ohmyapp.library.transaction.entity.Record;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Emerald on 9/4/2016.
 *
 */
public class PojoCopyTest extends DaoBaseTest {
    private static final Logger LOGGER = Logger.getLogger(PojoCopyTest.class);
    private static final Gson gson = new Gson();

    @Test
    public void testCopyRecord() throws LibraryException, InvocationTargetException, IllegalAccessException {
        String randomString = new SimpleDateFormat("SSS").format(new Date());
        Record record = createRecord(randomString);
        RecordPojo recordPojo = new RecordPojo();
        BeanUtils.copyProperties(recordPojo, record);
        PatronPojo patronPojo = new PatronPojo();
        BeanUtils.copyProperties(patronPojo, record.getPatron());
        recordPojo.setPatronPojo(patronPojo);
        MediaCopyPojo mediaCopyPojo = new MediaCopyPojo();
        BeanUtils.copyProperties(mediaCopyPojo, record.getMediaCopy());
        MediaPojo mediaPojo = new MediaPojo();
        BeanUtils.copyProperties(mediaPojo, record.getMediaCopy().getParent());
        recordPojo.setMediaPojo(mediaPojo);
        LOGGER.debug(gson.toJson(recordPojo));
    }
}
