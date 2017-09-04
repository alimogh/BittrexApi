package com.corycharlton.bittrexapi;

import com.corycharlton.bittrexapi.internal.util.StringUtils;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
@SuppressWarnings("ConstantConditions")
public class BittrexApiClient_BuilderTest {
    // TODO: Not sure I like the name. Would be nice if JUnit supported more than
    // only level of nested classed. It looks like JUnit5 supports this with the
    // @Nested attribute

    private static final String _key = "12345";
    private static final String _secret = "54321";

    public static class When_constructor_is_called {

        @Test()
        public void it_should_not_throw_exception_if_key_and_secret_are_valid() {
            new BittrexApiClient.Builder(_key, _secret);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_key_is_empty() {
            new BittrexApiClient.Builder(StringUtils.EMPTY, _secret);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_key_is_null() {
            new BittrexApiClient.Builder(null, _secret);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_key_is_whitespace() {
            new BittrexApiClient.Builder(" ", _secret);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_secret_is_empty() {
            new BittrexApiClient.Builder(_key, StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_secret_is_null() {
            new BittrexApiClient.Builder(_key, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_secret_is_whitespace() {
            new BittrexApiClient.Builder(_key, " ");
        }
    }

    public static class When_downloader_is_called {
        @Test()
        public void it_should_not_throw_exception_if_downloader_is_valid() {
            final BittrexApiClient.Builder builder = new BittrexApiClient.Builder(_key, _secret);
            builder.downloader(new UrlConnectionDownloader());

            Assert.assertNotNull(builder.build());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_downloader_is_null() {
            new BittrexApiClient.Builder(_key, _secret).downloader(null);
        }
    }
}
