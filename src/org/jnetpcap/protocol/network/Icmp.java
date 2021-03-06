/*
 * Copyright (C) 2005, 2006, 2007, 2008, 2009, 2010 Sly Technologies, Inc.
 *
 * This file is part of jNetPcap.
 *
 * jNetPcap is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jnetpcap.protocol.network;

import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.JHeaderChecksum;
import org.jnetpcap.packet.JHeaderMap;
import org.jnetpcap.packet.JSubHeader;
import org.jnetpcap.packet.annotate.Dynamic;
import org.jnetpcap.packet.annotate.Field;
import org.jnetpcap.packet.annotate.Header;
import org.jnetpcap.packet.annotate.HeaderLength;
import org.jnetpcap.protocol.JProtocol;
import org.jnetpcap.util.checksum.Checksum;

// TODO: Auto-generated Javadoc
/**
 * ICMP header definition.
 * 
 * @author Mark Bednarczyk
 * @author Sly Technologies, Inc.
 */
@Header
public class Icmp
    extends
    JHeaderMap<Icmp> implements JHeaderChecksum {

	/**
	 * ICMP Destination Unreachable header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(length = 4, id = IcmpType.DESTINATION_UNREACHABLE_ID, nicname = "unreach")
	public static class DestinationUnreachable
	    extends
	    Reserved {
	}

	/**
	 * ICMP Echo header (ping) baseclass definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	public static abstract class Echo
	    extends
	    JSubHeader<Icmp> {

		/**
		 * Id.
		 * 
		 * @return the int
		 */
		@Field(offset = 0, length = 16, format = "%x")
		public int id() {
			return super.getUShort(0);
		}

		/**
		 * Sequence.
		 * 
		 * @return the int
		 */
		@Field(offset = 16, length = 16, format = "%x")
		public int sequence() {
			return super.getUShort(2);
		}
	};

	/**
	 * ICMP Echo Reply header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(id = IcmpType.ECHO_REPLY_ID, length = 4, nicname = "reply")
	public static class EchoReply
	    extends
	    Echo {

	}

	/**
	 * ICMP Echo Request header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(id = IcmpType.ECHO_REQUEST_ID, length = 4, nicname = "request")
	public static class EchoRequest
	    extends
	    Echo {

	}

	/**
	 * A table of Icmp sub-codes per Icmp type.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	public enum IcmpCode {
		
		/** The DESTINATIO n_ hos t_ admi n_ prohibited. */
		DESTINATION_HOST_ADMIN_PROHIBITED(IcmpType.DESTINATION_UNREACHABLE, 10),
		
		/** The DESTINATIO n_ hos t_ isolated. */
		DESTINATION_HOST_ISOLATED(IcmpType.DESTINATION_UNREACHABLE, 8),
		
		/** The DESTINATIO n_ hos t_ unknown. */
		DESTINATION_HOST_UNKNOWN(IcmpType.DESTINATION_UNREACHABLE, 7),
		
		/** The DESTINATIO n_ hos t_ unreachabl e_ fo r_ service. */
		DESTINATION_HOST_UNREACHABLE_FOR_SERVICE(
		    IcmpType.DESTINATION_UNREACHABLE, 12),
		
		/** The DESTINATIO n_ networ k_ admi n_ prohibited. */
		DESTINATION_NETWORK_ADMIN_PROHIBITED(IcmpType.DESTINATION_UNREACHABLE, 9),
		
		/** The DESTINATIO n_ networ k_ redirect. */
		DESTINATION_NETWORK_REDIRECT(IcmpType.DESTINATION_UNREACHABLE, 0),
		
		/** The DESTINATIO n_ networ k_ unreachable. */
		DESTINATION_NETWORK_UNREACHABLE(IcmpType.DESTINATION_UNREACHABLE, 6),
		
		/** The DESTINATIO n_ networ k_ unreachabl e_ fo r_ service. */
		DESTINATION_NETWORK_UNREACHABLE_FOR_SERVICE(
		    IcmpType.DESTINATION_UNREACHABLE, 11),
		
		/** The DESTINATIO n_ n o_ frag. */
		DESTINATION_NO_FRAG(IcmpType.DESTINATION_UNREACHABLE, 4),
		
		/** The DESTINATIO n_ por t_ unreachable. */
		DESTINATION_PORT_UNREACHABLE(IcmpType.DESTINATION_UNREACHABLE, 3),
		
		/** The DESTINATIO n_ protoco l_ unreachable. */
		DESTINATION_PROTOCOL_UNREACHABLE(IcmpType.DESTINATION_UNREACHABLE, 1),
		
		/** The DESTINATIO n_ sourc e_ route. */
		DESTINATION_SOURCE_ROUTE(IcmpType.DESTINATION_UNREACHABLE, 5),

		/** The PARAMETE r_ proble m_ missin g_ option. */
		PARAMETER_PROBLEM_MISSING_OPTION(IcmpType.PARAM_PROBLEM, 1),
		
		/** The PARAMETE r_ proble m_ wit h_ datagram. */
		PARAMETER_PROBLEM_WITH_DATAGRAM(IcmpType.PARAM_PROBLEM, 0),
		
		/** The REDIREC t_ host. */
		REDIRECT_HOST(IcmpType.REDIRECT, 1),
		
		/** The REDIREC t_ network. */
		REDIRECT_NETWORK(IcmpType.REDIRECT, 0),

		/** The REDIREC t_ servic e_ an d_ host. */
		REDIRECT_SERVICE_AND_HOST(IcmpType.REDIRECT, 3),
		
		/** The REDIREC t_ servic e_ an d_ network. */
		REDIRECT_SERVICE_AND_NETWORK(IcmpType.REDIRECT, 2),

		/** The TIM e_ exceede d_ durin g_ fra g_ reassembly. */
		TIME_EXCEEDED_DURING_FRAG_REASSEMBLY(IcmpType.TIME_EXCEEDED, 1),
		
		/** The TIM e_ exceede d_ i n_ transit. */
		TIME_EXCEEDED_IN_TRANSIT(IcmpType.TIME_EXCEEDED, 1), ;

		/**
		 * To string.
		 * 
		 * @param type
		 *          the type
		 * @param code
		 *          the code
		 * @return the string
		 */
		public static String toString(int type, int code) {
			for (IcmpCode t : values()) {
				if (t.type.id == type && t.code == code) {
					return t.description;
				}
			}

			return null;
		}

		/**
		 * Value of.
		 * 
		 * @param type
		 *          the type
		 * @param code
		 *          the code
		 * @return the icmp code
		 */
		public static IcmpCode valueOf(int type, int code) {
			for (IcmpCode t : values()) {
				if (t.type.id == type && t.code == code) {
					return t;
				}
			}

			return null;
		}

		/** The code. */
		private final int code;

		/** The description. */
		private final String description;

		/** The type. */
		private final IcmpType type;

		/**
		 * Instantiates a new icmp code.
		 * 
		 * @param type
		 *          the type
		 * @param code
		 *          the code
		 */
		private IcmpCode(IcmpType type, int code) {
			this.type = type;
			this.code = code;
			this.description = name().toString().toLowerCase().replace('_', ' ');
		}

		/**
		 * Instantiates a new icmp code.
		 * 
		 * @param type
		 *          the type
		 * @param code
		 *          the code
		 * @param description
		 *          the description
		 */
		private IcmpCode(IcmpType type, int code, String description) {
			this.type = type;
			this.code = code;
			this.description = description;

		}

		/**
		 * Gets the code.
		 * 
		 * @return the code
		 */
		public final int getCode() {
			return this.code;
		}

		/**
		 * Gets the description.
		 * 
		 * @return the description
		 */
		public final String getDescription() {
			return this.description;
		}

		/**
		 * Gets the type.
		 * 
		 * @return the type
		 */
		public final IcmpType getType() {
			return this.type;
		}
	}

	/**
	 * A table of IcmpTypes and their names.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	public enum IcmpType {
		
		/** The DESTINATIO n_ unreachable. */
		DESTINATION_UNREACHABLE(3, "destination unreachable"),
		
		/** The ECH o_ reply. */
		ECHO_REPLY(0, "echo reply"),
		
		/** The ECH o_ request. */
		ECHO_REQUEST(8, "echo request"),
		
		/** The INF o_ request. */
		INFO_REQUEST(15, "info request"),
		
		/** The INF o_ response. */
		INFO_RESPONSE(16, "info response"),
		
		/** The PARA m_ problem. */
		PARAM_PROBLEM(12, "parameter problem"),
		
		/** The REDIRECT. */
		REDIRECT(5, "redirect"),
		
		/** The SOURC e_ quench. */
		SOURCE_QUENCH(4, "source quench"),
		
		/** The TIM e_ exceeded. */
		TIME_EXCEEDED(11, "time exceeded"),
		
		/** The TIMESTAM p_ request. */
		TIMESTAMP_REQUEST(13, "timestamp request"),
		
		/** The TIMESTAM p_ response. */
		TIMESTAMP_RESPONSE(14, "timestamp response"),

		;

		/** The Constant DESTINATION_UNREACHABLE_ID. */
		public final static int DESTINATION_UNREACHABLE_ID = 3;

		/** The Constant ECHO_REPLY_ID. */
		public final static int ECHO_REPLY_ID = 0;

		/** The Constant ECHO_REQUEST_ID. */
		public final static int ECHO_REQUEST_ID = 8;

		/** The Constant INFO_REQUEST_ID. */
		public final static int INFO_REQUEST_ID = 15;

		/** The Constant INFO_RESPONSE_ID. */
		public final static int INFO_RESPONSE_ID = 16;

		/** The Constant PARAM_PROBLEM_ID. */
		public final static int PARAM_PROBLEM_ID = 12;

		/** The Constant REDIRECT_ID. */
		public final static int REDIRECT_ID = 5;

		/** The Constant SOURCE_QUENCH_ID. */
		public final static int SOURCE_QUENCH_ID = 4;

		/** The Constant TIME_EXCEEDED_ID. */
		public final static int TIME_EXCEEDED_ID = 11;

		/** The Constant TIMESTAMP_REQUEST_ID. */
		public final static int TIMESTAMP_REQUEST_ID = 13;

		/** The Constant TIMESTAMP_RESPONSE_ID. */
		public final static int TIMESTAMP_RESPONSE_ID = 14;

		/**
		 * To string.
		 * 
		 * @param id
		 *          the id
		 * @return the string
		 */
		public static String toString(int id) {
			for (IcmpType t : values()) {
				if (t.id == id) {
					return t.description;
				}
			}

			return null;
		}

		/**
		 * Value of.
		 * 
		 * @param type
		 *          the type
		 * @return the icmp type
		 */
		public static IcmpType valueOf(int type) {
			for (IcmpType t : values()) {
				if (t.id == type) {
					return t;
				}
			}

			return null;
		}

		/** The description. */
		private final String description;

		/** The id. */
		public final int id;

		/**
		 * Instantiates a new icmp type.
		 * 
		 * @param id
		 *          the id
		 */
		private IcmpType(int id) {
			this.id = id;
			this.description = name().toLowerCase().replace('_', ' ');
		}

		/**
		 * Instantiates a new icmp type.
		 * 
		 * @param id
		 *          the id
		 * @param description
		 *          the description
		 */
		private IcmpType(int id, String description) {
			this.id = id;
			this.description = description;

		}

		/**
		 * Gets the description.
		 * 
		 * @return the description
		 */
		public final String getDescription() {
			return this.description;
		}

		/**
		 * Gets the id.
		 * 
		 * @return the id
		 */
		public final int getId() {
			return this.id;
		}

	}

	/**
	 * ICMP Paramater Protoblem header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(length = 4, id = IcmpType.PARAM_PROBLEM_ID)
	public static class ParamProblem
	    extends
	    JSubHeader<Icmp> {

		/**
		 * Pointer.
		 * 
		 * @return the int
		 */
		@Field(offset = 0, length = 8)
		public int pointer() {
			return getUByte(0);
		}

		/**
		 * Reserved.
		 * 
		 * @return the int
		 */
		@Field(offset = 8, length = 24)
		public int reserved() {
			return (int) (getUInt(0) & 0x00FFFFFFL);
		}
	}

	/**
	 * ICMP Redirect header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(length = 4, id = IcmpType.REDIRECT_ID)
	public static class Redirect
	    extends
	    JSubHeader<Icmp> {

		/**
		 * Gateway.
		 * 
		 * @return the byte[]
		 */
		public byte[] gateway() {
			return getByteArray(0, 4);
		}
	}

	/**
	 * Base class for various ICMP Headers that contain a reserved field.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	public static abstract class Reserved
	    extends
	    JSubHeader<Icmp> {

		/**
		 * Reserved.
		 * 
		 * @return the long
		 */
		public long reserved() {
			return getUInt(0);
		}
	}

	/**
	 * ICMP Source Quence header definition.
	 * 
	 * @author Mark Bednarczyk
	 * @author Sly Technologies, Inc.
	 */
	@Header(length = 4, id = IcmpType.SOURCE_QUENCH_ID)
	public static class SourceQuench
	    extends
	    Reserved {

	}

	/** The Constant ID. */
	public static final int ID = JProtocol.ICMP_ID;

	/**
	 * Header length.
	 * 
	 * @param buffer
	 *          the buffer
	 * @param offset
	 *          the offset
	 * @return the int
	 */
	@HeaderLength
	public static int headerLength(JBuffer buffer, int offset) {
		switch (buffer.getUByte(offset)) {
			case 0: // EchoReply
			case 8: // EchoRequest
				return buffer.size() - offset - 4;

			case 4: // SourceQuench
			case 5: // Redirect
			case 11: // Timestamp
			default:
				return 4;
		}
	}

	/**
	 * Checksum description.
	 * 
	 * @return the string
	 */
	@Dynamic(Field.Property.DESCRIPTION)
	public String checksumDescription() {

		if (isFragmented()) {
			return "supressed for fragments";
		}

		if (isPayloadTruncated()) {
			return "supressed for truncated packets";
		}

		final int crc16 = calculateChecksum();
		if (checksum() == crc16) {
			return "correct";
		} else {
			return "incorrect: 0x" + Integer.toHexString(crc16).toUpperCase();
		}
	}

	/**
	 * Retrieves the header's checksum.
	 * 
	 * @return header's stored checksum
	 */
	@Field(offset = 2 * 8, length = 16, format = "%x")
	public int checksum() {
		return super.getUShort(2);
	}

	/**
	 * Code.
	 * 
	 * @return the int
	 */
	@Field(offset = 1 * 8, length = 8, format = "%x")
	public int code() {
		return super.getUByte(1);
	}

	/**
	 * Code enum.
	 * 
	 * @return the icmp code
	 */
	public IcmpCode codeEnum() {
		return IcmpCode.valueOf(type(), code());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jnetpcap.packet.JHeaderMap#decodeUniqueSubHeaders()
	 */
	/**
	 * Decode header.
	 * 
	 * @see org.jnetpcap.packet.JHeader#decodeHeader()
	 */
	@Override
	protected void decodeHeader() {
		final int id = type();
		optionsOffsets[id] = 4;
		optionsBitmap = (1 << id);
		optionsLength[id] = getLength() - 4;

	}

	/**
	 * Type.
	 * 
	 * @return the int
	 */
	@Field(offset = 0 * 8, length = 8, format = "%x")
	public int type() {
		return super.getUByte(0);
	}

	/**
	 * Type description.
	 * 
	 * @return the string
	 */
	@Dynamic(Field.Property.DESCRIPTION)
	public String typeDescription() {
		return IcmpType.valueOf(type()).getDescription();
	}

	/**
	 * Type enum.
	 * 
	 * @return the icmp type
	 */
	public IcmpType typeEnum() {
		return IcmpType.valueOf(type());
	}

	/**
	 * Calculates a checksum using protocol specification for a header. Checksums
	 * for partial headers or fragmented packets (unless the protocol alows it)
	 * are not calculated.
	 * 
	 * @return header's calculated checksum
	 */
	public int calculateChecksum() {

		if (getIndex() == -1) {
			throw new IllegalStateException("Oops index not set");
		}

		final int ipOffset = getPreviousHeaderOffset();

		return Checksum.inChecksumShouldBe(checksum(), Checksum.icmp(packet,
		    ipOffset, this.getOffset()));
	}

	/**
	 * Checks if the checksum is valid, for un-fragmented packets. If a packet is
	 * fragmented, the checksum is not verified as data to is incomplete, but the
	 * method returns true none the less.
	 * 
	 * @return true if checksum checks out or if this is a fragment, otherwise if
	 *         the computed checksum does not match the stored checksum false is
	 *         returned
	 */
	public boolean isChecksumValid() {

		if (isFragmented()) {
			return true;
		}

		if (getIndex() == -1) {
			throw new IllegalStateException("Oops index not set");
		}

		final int ipOffset = getPreviousHeaderOffset();

		return Checksum.icmp(packet, ipOffset, this.getOffset()) == 0;
	}

}
