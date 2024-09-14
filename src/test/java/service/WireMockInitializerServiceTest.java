package service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.recime.challenge.service.WireMockInitializerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WireMockInitializerServiceTest {

    @Mock
    private WireMockServer wireMockServer;

    @InjectMocks
    private WireMockInitializerService wireMockInitializerService;

    @Test
    void testSetupStubs() {
        wireMockInitializerService.setupStubs();
        verify(wireMockServer).stubFor(any());
    }
}
