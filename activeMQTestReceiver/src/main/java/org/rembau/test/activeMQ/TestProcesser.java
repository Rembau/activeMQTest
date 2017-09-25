package org.rembau.test.activeMQ;

import com.unilife.base.GsonUtil;
import com.unilife.commons.jms.JmsMessage;
import com.unilife.commons.jms.MessageProcesser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rembau on 2016/6/30.
 */
public class TestProcesser implements MessageProcesser {
    private final static Logger logger = LoggerFactory.getLogger(TestProcesser.class);


    @Override
    public void process(JmsMessage jmsMessage) {
        logger.info("===========" + GsonUtil.toJson(jmsMessage));
    }
}
