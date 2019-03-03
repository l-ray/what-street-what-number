package de.lray.addressline;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.tokenizer.token.Token;

import javax.json.JsonObject;

/**
 * Converts a given input string to a fitting JSON representation.
 *
 * The process is divided into the phases extraction, transformation and load.
 */
final class AddressLineConverter {

    private final Tokenizer _tokenizer;
    private final Mapper _mapper;

    AddressLineConverter(Tokenizer tokenizer, Mapper mapper) {
        if (tokenizer == null || mapper == null) {
            throw new NullPointerException();
        }
        _tokenizer = tokenizer;
        _mapper = mapper;
    }

    /**
     * Converts a given String to a JSON with the format
     *
     * @param addressAsString input representing an address holding street- and house number information.
     * @return JSON object
     * @throws IllegalArgumentException in case no mapping of the input to a valid address was possible.
     */
    public JsonObject asJSON(String addressAsString) throws IllegalArgumentException {
        Token[] addressToken = _tokenizer.tokenize(addressAsString);
        AddressLine result = _mapper.map(addressToken);
        if (result == null) {
            throw new IllegalArgumentException("Argument is not a mappable address :" + addressAsString );
        }
        return Serializer.serialize(result);
    }
}
